package com.distribuida.config;

import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class DbConfig {

    @Produces
    public DbClient dbClient() {
        return DbClient.builder()
                .config(Config.create().get("db"))
                .build();
    }

}
