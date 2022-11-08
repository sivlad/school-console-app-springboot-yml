package ua.com.foxminded.consoleschoolappspringboot.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.consoleschoolappspringboot.exception.FileException;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;
import ua.com.foxminded.consoleschoolappspringboot.model.Student;
import ua.com.foxminded.consoleschoolappspringboot.model.StudentsToCourse;
import ua.com.foxminded.consoleschoolappspringboot.service.schoolinitservice.SchoolInitService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@Service
public class SchoolDbInitializer {

    @Autowired
    private SchoolInitService schoolInitService;

    private static final Random RANDOM_GENERATOR = new Random();

    public void deleteAllRowsInDB() {
        schoolInitService.deleteAllRowsInDB();
        log.debug("All rows in DB was deleted");
    }

    public void createRandomGroups() {
        List<Group> groups = new ArrayList<>();
        for (int count = 0; count < 10; count++) {
            StringBuilder groupName = new StringBuilder();

            groupName.append((char)(RANDOM_GENERATOR.nextInt(26) + 'a'))
                    .append((char)(RANDOM_GENERATOR.nextInt(26) + 'a'))
                    .append("-")
                    .append((char)(RANDOM_GENERATOR.nextInt(10) + '0'))
                    .append((char)(RANDOM_GENERATOR.nextInt(10) + '0'));
            Group group = new Group();
            group.setGroupName(groupName.toString());

            groups.add(group);
        }
        schoolInitService.saveGroupList(groups);
        log.debug("Random groups are created");
    }

    public void createCourses() throws FileException {
        String coursesFileName = PropertyFactory.getInstance().getProperty().getProperty("data.courses");

        List<Course> courses = new ArrayList<>();
        try (Stream<String> lineStream = Files.lines(Paths.get(coursesFileName))) {
            for (var currentString : lineStream.collect(Collectors.toList())) {
                Course course = new Course();
                course.setCourseName(currentString);
                course.setCourseDescription("This is " + currentString + " faculty");

                courses.add(course);
            }
            schoolInitService.saveCourseList(courses);
            log.debug("Random courses are created");
        } catch (IOException exception) {
            log.error("Error open source courses file");
            throw new FileException("Error with source courses file");
        }
    }

    public void createRandomStudents() throws FileException {
        TxtFileReader txtFileReaderNames = new TxtFileReader("data.names");
        List<String> randomNames = txtFileReaderNames.readFile();
        TxtFileReader txtFileReaderLastNames = new TxtFileReader("data.lastnames");
        List<String> randomLastNames = txtFileReaderLastNames.readFile();

        List<Group> groups = schoolInitService.finaAllGroups();

        List<Student> studentsToAdd = new ArrayList<>();
        for (int count = 0; count < 200; count++) {
            Student addStudent = new Student();
            addStudent.setFirstName(randomNames.get(RANDOM_GENERATOR.nextInt(randomNames.size())));
            addStudent.setLastName(randomLastNames.get(RANDOM_GENERATOR.nextInt(randomLastNames.size())));
            addStudent.setGroupId(groups.get(RANDOM_GENERATOR.nextInt(groups.size())).getId());

            studentsToAdd.add(addStudent);
        }
        schoolInitService.saveStudentsList(studentsToAdd);
        log.debug("Random students are created");
    }

    public void assignStudentsToCourses() {
        List<Course> courses = schoolInitService.findAllCourses();
        List<Student> students = schoolInitService.findAllStudents();

        for (var currentStudent : students) {
            for (int count = 0; count < (RANDOM_GENERATOR.nextInt(2) + 1); count++) {
                StudentsToCourse studentsToCourse = new StudentsToCourse();
                studentsToCourse.setStudentId(currentStudent.getId());
                studentsToCourse.setCourseId(courses.get(RANDOM_GENERATOR.nextInt(courses.size())).getId());
                schoolInitService.assignStudentsToCourse(studentsToCourse);
            }
        }
        log.debug("Courses assign to students");
    }
}
