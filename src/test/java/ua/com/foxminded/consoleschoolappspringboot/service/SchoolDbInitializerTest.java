package ua.com.foxminded.consoleschoolappspringboot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.consoleschoolappspringboot.AppStarter;
import ua.com.foxminded.consoleschoolappspringboot.ConsoleSchoolAppSpringbootApplication;
import ua.com.foxminded.consoleschoolappspringboot.TestConteinersConfiguration;
import ua.com.foxminded.consoleschoolappspringboot.dao.courseDAO.CourseDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO.GroupDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentDAO.StudentDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentandcourseDAO.StudentsToCoursesDao;
import ua.com.foxminded.consoleschoolappspringboot.exception.FileException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts = {"classpath:schema.sql"})
@ContextConfiguration(classes = ConsoleSchoolAppSpringbootApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
public class SchoolDbInitializerTest {

    @Autowired
    private TestConteinersConfiguration testConteinersConfiguration;

    @MockBean
    private AppStarter appStarter;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StudentsToCoursesDao studentsToCoursesDao;

    @Autowired
    private SchoolDbInitializer schoolDbInitializer;

    @Test
    public void createRandomGroups_ShuldCreateRandomGroupsInDB_WhenCalled() {

        schoolDbInitializer.createRandomGroups();

        int expectedResult = 10;
        int actualResult = groupDao.findAll().size();

        assertEquals(expectedResult,
                actualResult,
                "Execute method createRandomGroups from SchoolDbInitializer");
    }

    @Test
    public void createCourses_ShuldCreateGroupsInDB_WhenCalled() {

        try {
            schoolDbInitializer.createCourses();
        } catch (FileException e) {
            e.printStackTrace();
        }

        int expectedResult = 10;
        int actualResult = courseDao.findAll().size();

        assertEquals(expectedResult,
                actualResult,
                "Execute method createCourses from SchoolDbInitializer");
    }

    @Test
    public void createRandomStudents_ShuldCreateRamdonStudentsInDB_WhenCalled() {

        schoolDbInitializer.createRandomGroups();
        try {
            schoolDbInitializer.createRandomStudents();
        } catch (FileException e) {
            e.printStackTrace();
        }

        int expectedResult = 200;
        int actualResult = studentDao.findAll().size();

        assertEquals(expectedResult,
                actualResult,
                "Execute method createRandomStudents from SchoolDbInitializer");
    }

    @Test
    public void deleteAllRowsInDB_ShuldDeleteAllRowsInDb_WhenCalled() {

        schoolDbInitializer.createRandomGroups();
        try {
            schoolDbInitializer.createCourses();
            schoolDbInitializer.createRandomStudents();
        } catch (FileException e) {
            e.printStackTrace();
        }
        schoolDbInitializer.assignStudentsToCourses();

        schoolDbInitializer.deleteAllRowsInDB();

        int expectedResult = 0;
        int actualResult = studentDao.findAll().size();

        assertEquals(expectedResult,
                actualResult,
                "Student Table is not empty");

        actualResult = courseDao.findAll().size();

        assertEquals(expectedResult,
                actualResult,
                "Courses Table is not empty");

        actualResult = groupDao.findAll().size();

        assertEquals(expectedResult,
                actualResult,
                "Groups Table is not empty");
    }

}
