package hu.nagyantal.springblog.gateway.config;


import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@Profile("dev")
public class DatabaseConfiguration {

    Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Value("${h2-server.port}")
    Integer h2ConsolePort;

    private Server webserver;

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws SQLException {
        logger.info("starting h2 console at port "+ h2ConsolePort);
        this.webserver = Server.createWebServer("-webPort", h2ConsolePort.toString(), "-tcpAllowOthers")
                .start();
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        logger.info("stopping h2 console at port " + h2ConsolePort);
        this.webserver.stop();
    }




}
