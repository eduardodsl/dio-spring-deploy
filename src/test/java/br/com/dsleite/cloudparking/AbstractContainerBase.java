package br.com.dsleite.cloudparking;

import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractContainerBase {
    
    static final PostgreSQLContainer POSTGRE_SQL_CONTAINER = null;

    static {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("container-master");
    }

}
