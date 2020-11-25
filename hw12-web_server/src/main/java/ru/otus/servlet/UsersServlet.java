package ru.otus.servlet;

import ru.otus.db.model.User;
import ru.otus.db.service.UserService;
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

    private final UserService userService;
    private final TemplateProcessor templateProcessor;

    public UsersServlet(TemplateProcessor templateProcessor, UserService userService) {
        this.templateProcessor = templateProcessor;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        final List<User> users = userService.findAll();
        final Map<String, Object> params = Map.of(TEMPLATE_USERS, users);
        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, params));
    }

}
