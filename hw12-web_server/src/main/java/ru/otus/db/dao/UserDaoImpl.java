package ru.otus.db.dao;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.db.model.User;
import ru.otus.db.sessionmanager.SessionManager;
import ru.otus.db.sessionmanager.DatabaseSessionHibernate;
import ru.otus.db.sessionmanager.SessionManagerHibernate;

import java.util.*;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private final SessionManagerHibernate sessionManager;

    public UserDaoImpl(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public List<User> findAll() {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return currentSession
                    .getHibernateSession()
                    .createQuery("SELECT u FROM User u", User.class).getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<User> findById(long id) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().find(User.class, id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public long insert(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.persist(user);
            hibernateSession.flush();
            return user.getId();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(
                    (User) currentSession.getHibernateSession()
                            .createQuery("FROM User u WHERE u.name=:userName")
                            .setParameter("userName", login).uniqueResult());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
