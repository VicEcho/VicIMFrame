package com;

import com.Server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Vic Zhang
 * @date 2020/2/24 3:44 PM
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private Server server;

    public static void main(String[] args) {
        new SpringApplication(Application.class).run();
    }


    @Override
    public void run(String... args) throws Exception {
        server.startServer();
    }
}
