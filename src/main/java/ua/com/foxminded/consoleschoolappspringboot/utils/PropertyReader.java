package ua.com.foxminded.consoleschoolappspringboot.utils;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.com.foxminded.consoleschoolappspringboot.exception.PropertyFileException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
@Service
public class PropertyReader {

    public Properties readProperties() throws PropertyFileException {
        Properties property = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/application1.properties")){
            property.load(fileInputStream);
            return property;
        } catch (IOException e) {
            log.error("Bad name of property file");
            throw new PropertyFileException("Error open file property");
        }
    }
}
