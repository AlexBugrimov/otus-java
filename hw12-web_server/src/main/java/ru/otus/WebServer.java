package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.otus.db.dao.UserDao;
import ru.otus.db.dao.UserDaoImpl;
import ru.otus.db.model.Address;
import ru.otus.db.model.User;
import ru.otus.db.service.UserService;
import ru.otus.db.service.UserServiceImpl;
import ru.otus.db.sessionmanager.SessionManagerHibernate;
import ru.otus.flyway.MigrationsExecutorFlyway;
import ru.otus.server.UsersWebServer;
import ru.otus.server.UsersWebServerWithAuth;
import ru.otus.services.TemplateProcessor;
import ru.otus.services.TemplateProcessorImpl;
import ru.otus.services.UserAuthService;
import ru.otus.services.UserAuthServiceImpl;
import ru.otus.utils.HibernateUtils;

/*
    // Стартовая страница
    http://localhost:8088

    // Страница пользователей
    http://localhost:8088/users

    // REST сервис
    http://localhost:8088/api/user/3
*/
public class WebServer {
    private static final int WEB_SERVER_PORT = 8088;
    private static final String TEMPLATES_DIR = "/templates/";
    private static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) throws Exception {

        final SessionManagerHibernate hibernateSessionManager = getHibernateSessionManager();
        migration();
        UserDao userDao = new UserDaoImpl(hibernateSessionManager);
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        final UserService userService = new UserServiceImpl(userDao);
        UserAuthService authService = new UserAuthServiceImpl(userService);

        UsersWebServer usersWebServer = new UsersWebServerWithAuth(
                WEB_SERVER_PORT, authService, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }

    private static void migration() {
        new MigrationsExecutorFlyway(HIBERNATE_CFG_FILE).executeMigrations();
    }

    private static SessionManagerHibernate getHibernateSessionManager() {
        var sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE,
                User.class, Address.class);
        return new SessionManagerHibernate(sessionFactory);
    }
}
