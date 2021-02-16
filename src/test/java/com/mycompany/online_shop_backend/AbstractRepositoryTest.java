package com.mycompany.online_shop_backend;

import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractRepositoryTest {

    private static final PostgreSQLContainer<PostgresContainer> POSTGRE_SQL_CONTAINER = PostgresContainer.getInstance();

    static {
        POSTGRE_SQL_CONTAINER.start();
    }
}
