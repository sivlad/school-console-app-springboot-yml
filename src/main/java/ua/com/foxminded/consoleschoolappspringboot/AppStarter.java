package ua.com.foxminded.consoleschoolappspringboot;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.com.foxminded.consoleschoolappspringboot.menu.MenuExecutor;
import ua.com.foxminded.consoleschoolappspringboot.utils.SchoolInitializer;

@Log4j2
@Configuration
public class AppStarter {

    @Autowired
    private MenuExecutor menuExecutor;

    @Autowired
    private SchoolInitializer schoolInitializer;

    @Bean
    public ApplicationRunner init() {
        log.info("ApplicationRunner has started");
        return args -> {
            schoolInitializer.schoolInitialize();
            menuExecutor.startMenu();
        };
    }
}
