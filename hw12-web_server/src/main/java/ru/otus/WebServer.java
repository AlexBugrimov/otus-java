package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.otus.core.dao.Dao;
import ru.otus.core.model.AddressDataSet;
import ru.otus.core.model.PhoneDataSet;
import ru.otus.core.model.User;
import ru.otus.core.service.DbService;
import ru.otus.core.service.DbServiceImpl;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;
import ru.otus.hibernate.utils.HibernateUtils;
import ru.otus.server.UsersWebServer;
import ru.otus.server.UsersWebServerWithFilterBasedSecurity;
import ru.otus.services.TemplateProcessor;
import ru.otus.services.TemplateProcessorImpl;
import ru.otus.services.UserAuthService;
import ru.otus.services.UserAuthServiceImpl;

import java.util.Arrays;
import java.util.List;

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
        Dao<User> userDao = new UserDaoHibernate(getHibernateSessionManager());
        initDb(userDao);
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(userDao);

        UsersWebServer usersWebServer = new UsersWebServerWithFilterBasedSecurity(WEB_SERVER_PORT,
                authService, userDao, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }

    private static SessionManagerHibernate getHibernateSessionManager() {
        var sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE,
                User.class, AddressDataSet.class, PhoneDataSet.class);
        return new SessionManagerHibernate(sessionFactory);
    }

    private static void initDb(Dao<User> userDao) {
        final DbService<User> userDbService = new DbServiceImpl<>(userDao);
        final List<User> users = Arrays.asList(new User()
                        .setName("Alex")
                        .setPassword("password")
                        .setAddressDataSet(new AddressDataSet("ул. Ленина 15"))
                        .setPhones("89111231122", "89991233000", "89151230102"),
                new User()
                        .setName("Ivan")
                        .setPassword("password")
                        .setAddressDataSet(new AddressDataSet("ул. Зеленодольская 3"))
                        .setPhones("89231669192"));
        users.forEach(userDbService::save);
    }
}
