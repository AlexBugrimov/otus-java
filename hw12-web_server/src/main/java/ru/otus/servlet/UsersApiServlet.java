package ru.otus.servlet;

import com.google.gson.Gson;
import ru.otus.core.dao.Dao;
import ru.otus.core.model.User;
import ru.otus.core.service.DbService;
import ru.otus.core.service.DbServiceImpl;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;


public class UsersApiServlet extends HttpServlet {

    private static final int ID_PATH_PARAM_POSITION = 1;

    private final DbService<User> userDbService;
    private final Gson gson;

    public UsersApiServlet(Dao<User> userDao, Gson gson) {
        this.userDbService = new DbServiceImpl<>(userDao);
        this.gson = gson;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userDbService.getById(extractIdFromRequest(request)).orElse(null);

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        out.print(gson.toJson(user));
    }

    private long extractIdFromRequest(HttpServletRequest request) {
        String[] path = request.getPathInfo().split("/");
        String id = (path.length > 1)? path[ID_PATH_PARAM_POSITION]: String.valueOf(- 1);
        return Long.parseLong(id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try(BufferedReader reader = request.getReader()) {
            final User user = gson.fromJson(reader.readLine(), User.class);
            userDbService.save(user);
        }
    }
}
