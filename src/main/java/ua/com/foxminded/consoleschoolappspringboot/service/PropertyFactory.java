package ua.com.foxminded.consoleschoolappspringboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.foxminded.consoleschoolappspringboot.AppStarter;
import ua.com.foxminded.consoleschoolappspringboot.exception.PropertyFileException;

import java.util.Properties;

public class PropertyFactory {

    private static PropertyFactory propertyFactory;
    private static Properties properties;
    private static final PropertyReader PROPERTY_READER;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    static {
        PROPERTY_READER = new PropertyReader();
        try {
            properties = PROPERTY_READER.readProperties();
        } catch (PropertyFileException e) {
            LOGGER.info(e.getMessage());
        }
    }

    public static PropertyFactory getInstance() {
        if (propertyFactory == null) {
            propertyFactory = new PropertyFactory();
        }
        return propertyFactory;
    }

    public Properties getProperty() {
        return properties;
    }
}
