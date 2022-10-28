package ua.com.foxminded.consoleschoolappspringboot.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.com.foxminded.consoleschoolappspringboot.AppStarter;
import ua.com.foxminded.consoleschoolappspringboot.dao.courseDAO.CourseDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO.GroupDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentDAO.StudentDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentandcourseDAO.StudentsToCoursesDaoImpl;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MenuPublisherTest {

    @MockBean
    AppStarter appStarter;

    @MockBean
    private StudentDao studentDao;

    @MockBean
    private CourseDao courseDao;

    @MockBean
    private GroupDao groupDao;

    @MockBean
    private StudentsToCoursesDaoImpl studentsToCoursesDao;

    @Autowired
    private MenuPublisher publisher;

    @Autowired
    private MenuExecutor menuExecutor;

    @Test
    void showCoursesList_ShuldReturnListOfCourses_WhenCalled() {

        List<Course> courses = new ArrayList<>();
        Course addCourse = new Course();
        addCourse.setCourseName("mathematics");
        addCourse.setCourseDescription("This is mathematics faculty");
        addCourse.setId(1L);
        courses.add(addCourse);
        addCourse = new Course();
        addCourse.setCourseName("biology");
        addCourse.setCourseDescription("This is biology faculty");
        addCourse.setId(2L);
        courses.add(addCourse);
        addCourse = new Course();
        addCourse.setCourseName("physics");
        addCourse.setCourseDescription("This is physics faculty");
        addCourse.setId(3L);
        courses.add(addCourse);

        when(courseDao.findAll()).thenReturn(courses);

        String expectedResult = "List of courses\n" +
                "------------------------------------------------------------\n" +
                "1.  mathematics.  This is mathematics faculty\n" +
                "2.  biology.  This is biology faculty\n" +
                "3.  physics.  This is physics faculty\n" +
                "------------------------------------------------------------\n";
        String actualResult = publisher.showCoursesList();
        assertEquals(expectedResult,
                actualResult,
                "Execute method showCoursesList from courses");
    }

    @Test
    void showGroupList_ShuldReturnListOfGroups_WhenCalled() {

        List<Group> groups = new ArrayList<>();

        Group addGroup = new Group();
        addGroup.setGroupName("kl-69");
        addGroup.setId(1L);
        groups.add(addGroup);
        addGroup = new Group();
        addGroup.setGroupName("bg-23");
        addGroup.setId(2L);
        groups.add(addGroup);
        addGroup = new Group();
        addGroup.setGroupName("pk-68");
        addGroup.setId(3L);
        groups.add(addGroup);

        when(groupDao.findAll()).thenReturn(groups);

        String expectedResult = "List of groups\n" +
                "------------------------------------------------------------\n" +
                "1.  kl-69\n" +
                "2.  bg-23\n" +
                "3.  pk-68\n" +
                "------------------------------------------------------------\n";
        String actualResult = publisher.showGroupList();
        assertEquals(expectedResult,
                actualResult,
                "Execute method showGroupList from groups");

    }

    @Test
    void showCoursesListStudent_ShuldReturnListOfStudentsCourses_WhenCalled() {

        List<Course> courses = new ArrayList<>();
        Course addCourse = new Course();
        addCourse.setCourseName("mathematics");
        addCourse.setCourseDescription("This is mathematics faculty");
        addCourse.setId(1L);
        courses.add(addCourse);
        addCourse = new Course();
        addCourse.setCourseName("biology");
        addCourse.setCourseDescription("This is biology faculty");
        addCourse.setId(2L);
        courses.add(addCourse);
        addCourse = new Course();
        addCourse.setCourseName("physics");
        addCourse.setCourseDescription("This is physics faculty");
        addCourse.setId(3L);
        courses.add(addCourse);

        long studentId = 1L;
        when(courseDao.findAllCoursesFromStudent(studentId)).thenReturn(courses);

        String expectedResult = "List of courses\n" +
                "------------------------------------------------------------\n" +
                "1.  mathematics.  This is mathematics faculty\n" +
                "2.  biology.  This is biology faculty\n" +
                "3.  physics.  This is physics faculty\n" +
                "------------------------------------------------------------\n";
        String actualResult = publisher.showCoursesListStudent(studentId);
        assertEquals(expectedResult,
                actualResult,
                "Execute method showCoursesListStudent from courses");
    }

    @Test
    void showMenu_ShuldReturnMenuInString_WhenCalled() {

        String expectedResult = "To do action press the number\n" +
                "------------------------------------------------------------\n" +
                "1.  Find all groups with less or equal students’ number\n" +
                "2.  Find all students related to the course with the given name\n" +
                "3.  Add a new student\n" +
                "4.  Delete a student by the STUDENT_ID\n" +
                "5.  Add a student to the course (from a list)\n" +
                "6.  Remove the student from one of their courses.\n" +
                "------------------------------------------------------------\n" +
                "Please enter the number";
        String actualResult = publisher.showMenu();
        assertEquals(expectedResult,
                actualResult,
                "Execute method showMenu from courses");
    }
}
