package ua.com.foxminded.consoleschoolappspringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.com.foxminded.consoleschoolappspringboot.menu.MenuExecutor;
import ua.com.foxminded.consoleschoolappspringboot.service.SchoolInitializer;

@Configuration
public class AppStarter {

    @Autowired
    private MenuExecutor menuExecutor;

    @Autowired
    private SchoolInitializer schoolInitializer;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    @Bean
    public ApplicationRunner init() {
        LOGGER.info("ApplicationRunner has started");
        return args -> {
//            schoolInitializer.schoolInitialize();
//            menuExecutor.startMenu();
        };
    }
}
