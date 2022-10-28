package ua.com.foxminded.consoleschoolappspringboot.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.foxminded.consoleschoolappspringboot.AppStarter;
import ua.com.foxminded.consoleschoolappspringboot.exception.FileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TxtFileReader {

    private final String filename;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    public TxtFileReader(String filename) {
        this.filename = filename;
    }

    public List<String> readFile() throws FileException {
        Properties property = PropertyFactory.getInstance().getProperty();

        try (Stream<String> lineStream = Files.lines(Paths.get(property.getProperty(filename)))) {
            return lineStream.collect(Collectors.toList());
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
            throw new FileException("Error with file");
        }
    }

}
