package ua.com.foxminded.consoleschoolappspringboot.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.foxminded.consoleschoolappspringboot.AppStarter;
import ua.com.foxminded.consoleschoolappspringboot.exception.PropertyFileException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    public Properties readProperties() throws PropertyFileException {
        Properties property = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/application1.properties")){
            property.load(fileInputStream);
            return property;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new PropertyFileException("Error open file property");
        }
    }
}
