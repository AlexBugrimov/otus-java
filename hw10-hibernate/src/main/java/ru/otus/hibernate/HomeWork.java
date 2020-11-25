package ru.otus.hibernate;

import ru.otus.db.dao.Dao;
import ru.otus.db.model.AddressDataSet;
import ru.otus.db.model.PhoneDataSet;
import ru.otus.db.model.User;
import ru.otus.db.service.DbService;
import ru.otus.db.service.DbServiceImpl;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.db.sessionmanager.SessionManagerHibernate;
import ru.otus.hibernate.utils.HibernateUtils;

public class HomeWork {

    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) {

        var sessionManager = getHibernateSessionManager();
        final Dao<User> userDao = new UserDaoHibernate(sessionManager);

        final DbService<User> dbService = new DbServiceImpl<>(userDao);

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
    }

    private static SessionManagerHibernate getHibernateSessionManager() {
        var sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE,
                User.class, AddressDataSet.class, PhoneDataSet.class);

        return new SessionManagerHibernate(sessionFactory);
    }
}
