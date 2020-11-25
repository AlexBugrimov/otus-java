package ru.otus;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.db.dao.Dao;
import ru.otus.db.model.Account;
import ru.otus.db.model.User;
import ru.otus.db.service.DbServiceImpl;
import ru.otus.h2.DataSourceH2;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.jdbc.dao.AccountDaoJdbcMapper;
import ru.otus.jdbc.dao.UserDaoJdbcMapper;
import ru.otus.jdbc.mapper.EntityClassMetaDataImpl;
import ru.otus.jdbc.mapper.JdbcMapperMetaData;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.Optional;


public class HomeWork {
    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
        // Общая часть
        var dataSource = new DataSourceH2();
        flywayMigrations(dataSource);
        var sessionManager = new SessionManagerJdbc(dataSource);

        // Работа с пользователем
        final Dao<User> userDao = new UserDaoJdbcMapper(
                new JdbcMapperMetaData<>(
                        sessionManager,
                        new DbExecutorImpl<>(),
                        EntityClassMetaDataImpl.of(User.class)
                )
        );

        // Код дальше должен остаться, т.е. userDao должен использоваться
        var dbServiceUser = new DbServiceImpl<>(userDao);
        var userId = dbServiceUser.save(new User(0, "User Name"));
        Optional<User> user = dbServiceUser.getById(userId);

        user.ifPresentOrElse(
                crUser -> logger.info("created user, name:{}", crUser.getName()),
                () -> logger.info("user was not created")
        );

        // Работа со счетом
        final Dao<Account> accountDao = new AccountDaoJdbcMapper(
                new JdbcMapperMetaData<>(
                        sessionManager,
                        new DbExecutorImpl<>(),
                        EntityClassMetaDataImpl.of(Account.class)
                )
        );

        var dbServiceAccount = new DbServiceImpl<>(accountDao);
        var accountId = dbServiceAccount.save(new Account("Premium Type", BigDecimal.TEN));
        Optional<Account> account = dbServiceAccount.getById(accountId);

        account.ifPresentOrElse(
                crAccount -> logger.info("created Account, type:{}", crAccount.getType()),
                () -> logger.info("account was not created")
        );
    }

    private static void flywayMigrations(DataSource dataSource) {
        logger.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        logger.info("db migration finished.");
        logger.info("***");
    }
}
