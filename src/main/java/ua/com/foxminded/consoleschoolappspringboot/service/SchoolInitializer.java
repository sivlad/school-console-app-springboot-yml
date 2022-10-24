package ua.com.foxminded.consoleschoolappspringboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.consoleschoolappspringboot.AppStarter;
import ua.com.foxminded.consoleschoolappspringboot.exception.FileException;

@Service
public class SchoolInitializer {

    @Autowired
    private SchoolDbInitializer schoolDbInitializer;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    public void schoolInitialize() {
        try {
            schoolDbInitializer.deleteAllRowsInDB();
            schoolDbInitializer.createRandomGroups();
            schoolDbInitializer.createCourses();
            schoolDbInitializer.createRandomStudents();
            schoolDbInitializer.assignStudentsToCourses();
        } catch (FileException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
