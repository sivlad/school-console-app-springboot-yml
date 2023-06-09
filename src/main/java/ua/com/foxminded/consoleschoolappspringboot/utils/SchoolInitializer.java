package ua.com.foxminded.consoleschoolappspringboot.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.com.foxminded.consoleschoolappspringboot.exception.FileException;

@Log4j2
@Service
public class SchoolInitializer {

    private final SchoolDbInitializer schoolDbInitializer;

    public SchoolInitializer(SchoolDbInitializer schoolDbInitializer) {
        this.schoolDbInitializer = schoolDbInitializer;
    }

    public void schoolInitialize() {
        try {
            log.info("Start school initialize");
            schoolDbInitializer.deleteAllRowsInDB();
            schoolDbInitializer.createRandomGroups();
            schoolDbInitializer.createCourses();
            schoolDbInitializer.createRandomStudents();
            schoolDbInitializer.assignStudentsToCourses();
            log.info("End school initialize");
        } catch (FileException e) {
            log.error("error with source file names");
        }
    }
}
