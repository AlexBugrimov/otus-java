package ru.otus;

import ru.otus.cachehw.Cache;
import ru.otus.cachehw.DbCache;
import ru.otus.core.dao.Dao;
import ru.otus.core.model.AddressDataSet;
import ru.otus.core.model.PhoneDataSet;
import ru.otus.core.model.User;
import ru.otus.core.service.DbService;
import ru.otus.core.service.DbServiceWithCache;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;
import ru.otus.hibernate.utils.HibernateUtils;
import ru.otus.listeners.AdminListener;
import ru.otus.listeners.Listener;
import ru.otus.listeners.ModeratorListener;

public class HomeWork {

    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) {

        var sessionManager = getHibernateSessionManager();
        final Dao<User, Long> userDao = new UserDaoHibernate(sessionManager);

        final Cache<Long, User> cache = new DbCache<>();

        final Listener[] listeners = {
                new AdminListener<>(),
                new ModeratorListener<>()
        };
        cache.addListeners(listeners);

        final DbService<User, Long> dbService = new DbServiceWithCache<>(userDao, cache);

        var userAlex = new User()
                .setName("Alex")
                .setAddressDataSet(new AddressDataSet("ул. Ленина 15"))
                .setPhones("89111231122", "89991233000", "89151230102");
        var userIvan = new User()
                .setName("Ivan")
                .setAddressDataSet(new AddressDataSet("ул. Зеленодольская 3"))
                .setPhones("89231669192");

        var userAlexId = dbService.save(userAlex);
        dbService.save(userIvan);
        var userById = dbService.getById(userAlexId);
        userById.ifPresent(System.out::println);

        cache.removeListeners(listeners);
    }

    private static SessionManagerHibernate getHibernateSessionManager() {
        var sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE,
                User.class, AddressDataSet.class, PhoneDataSet.class);

        return new SessionManagerHibernate(sessionFactory);
    }
}
