package ru.otus.servlet;

import ru.otus.core.dao.Dao;
import ru.otus.core.model.User;
import ru.otus.core.service.DbService;
import ru.otus.core.service.DbServiceImpl;
import ru.otus.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class UsersServlet extends HttpServlet {

    private static final String USERS_PAGE_TEMPLATE = "users.html";
    private static final String TEMPLATE_USERS = "users";

    private final DbService<User> userDbService;
    private final TemplateProcessor templateProcessor;

    public UsersServlet(TemplateProcessor templateProcessor, Dao<User> userDao) {
        this.templateProcessor = templateProcessor;
        this.userDbService = new DbServiceImpl<>(userDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        final List<User> users = userDbService.findAll();
        final Map<String, Object> params = Map.of(TEMPLATE_USERS, users);
        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, params));
    }

}
