package ua.com.foxminded.consoleschoolappspringboot.service;


import ua.com.foxminded.consoleschoolappspringboot.exception.PropertyFileException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public Properties readProperties() throws PropertyFileException {
        Properties property = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/application1.properties")){
            property.load(fileInputStream);
            return property;
        } catch (IOException e) {
            throw new PropertyFileException("Error open file property");
        }
    }
}
