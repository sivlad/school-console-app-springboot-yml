package ua.com.foxminded.consoleschoolappspringboot.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.com.foxminded.consoleschoolappspringboot.AppStarter;
import ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO.GroupDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentDAO.StudentDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentandcourseDAO.StudentsToCoursesDaoImpl;
import ua.com.foxminded.consoleschoolappspringboot.model.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MenuExecutorTest {

    @MockBean
    AppStarter appStarter;

    @MockBean
    private StudentDao studentDao;

    @MockBean
    private GroupDao groupDao;

    @MockBean
    private StudentsToCoursesDaoImpl studentsToCoursesDao;

    @Autowired
    private MenuPublisher publisher;

    @Autowired
    private MenuExecutor menuExecutor;

    @Test
    void menuItem1Execute_ShouldReturnStringReprezantationOfListOfGroupsLessOreEqualStudentsNumbers_WhenCalled() {

        List<String> groupsList = new ArrayList<>();
        groupsList.add("ej-74");
        groupsList.add("wx-40");
        groupsList.add("eq-81");
        when(groupDao.findAllGroupsWithLessOreEqualStudentsNumber(20)).thenReturn(groupsList);

        String expectedResult = "------------------------------------------------------------\n" +
                "1.  ej-74\n" +
                "2.  wx-40\n" +
                "3.  eq-81\n" +
                "------------------------------------------------------------\n";
        String actualResult = menuExecutor.menuItem1Execute(20);
        assertEquals(expectedResult,
                actualResult,
                "Execute method findAllGroupsWithLessOreEqualStudentsNumber from courses");
    }

    @Test
    void menuItem2Execute_ShouldReturnStringReprezantationOfListOfStudentsRelatedToXourse_WhenCalled() {

        List<Student> studentsList = new ArrayList<>();
        Student addStudent = new Student();
        addStudent.setFirstName("Kiril");
        addStudent.setLastName("Sostin");
        addStudent.setGroupId(1L);
        studentsList.add(addStudent);
        addStudent = new Student();
        addStudent.setFirstName("Vyacheslav");
        addStudent.setLastName("Naiden");
        addStudent.setGroupId(2L);
        studentsList.add(addStudent);
        addStudent = new Student();
        addStudent.setFirstName("Broneslav");
        addStudent.setLastName("Samoilov");
        addStudent.setGroupId(2L);
        studentsList.add(addStudent);

        String courseName = "philosophy";
        when(studentDao.findAllFromCourse(courseName)).thenReturn(studentsList);

        String expectedResult = "------------------------------------------------------------\n" +
                "1.  Kiril Sostin\n" +
                "2.  Vyacheslav Naiden\n" +
                "3.  Broneslav Samoilov\n" +
                "------------------------------------------------------------\n";
        String actualResult = menuExecutor.menuItem2Execute(courseName);
        assertEquals(expectedResult,
                actualResult,
                "Execute method findAllGroupsWithLessOreEqualStudentsNumber from courses");

    }
}
