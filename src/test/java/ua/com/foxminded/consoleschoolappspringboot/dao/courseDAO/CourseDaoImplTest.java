package ua.com.foxminded.consoleschoolappspringboot.dao.courseDAO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import ua.com.foxminded.consoleschoolappspringboot.AppStarter;
import ua.com.foxminded.consoleschoolappspringboot.ConsoleSchoolAppSpringbootApplication;
import ua.com.foxminded.consoleschoolappspringboot.menu.MenuExecutor;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;
import ua.com.foxminded.consoleschoolappspringboot.service.SchoolInitializer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:test-data.sql"})
@ContextConfiguration(classes = ConsoleSchoolAppSpringbootApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
class CourseDaoImplTest {

//    @Autowired
//    @Mock
//    AppStarter appStarter;

    private List<Course> expectedResult = new ArrayList<>();

    private static final PostgreSQLContainer<?> postgresqlContainer;

    static {
        postgresqlContainer = new PostgreSQLContainer<>("postgres:14.5-alpine");
        postgresqlContainer.start();
    }

    @DynamicPropertySource
    public static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }

    @Autowired
    private CourseDao courseDao;

    @Test
    void findAll_ShouldReturnListOfCourses_WhenCalled() {
        Course addCourse = new Course();
        addCourse.setCourseName("mathematics");
        addCourse.setCourseDescription("mathematics course");
        addCourse.setId(1L);
        expectedResult.add(addCourse);
        addCourse = new Course();
        addCourse.setCourseName("biology");
        addCourse.setCourseDescription("biology course");
        addCourse.setId(2L);
        expectedResult.add(addCourse);

        List<Course> actualResult = courseDao.findAll();

        assertEquals(expectedResult,
                actualResult,
                "Execute method findAll from courses");
    }

    @Test
    void save_ShouldSaveCourseAndThenFindAllReturnListOfCoursesWithAddedCourse_WhenCalled() {
        Course addCourse = new Course();
        addCourse.setCourseName("mathematics");
        addCourse.setCourseDescription("mathematics course");
        addCourse.setId(1L);
        expectedResult.add(addCourse);
        addCourse = new Course();
        addCourse.setCourseName("biology");
        addCourse.setCourseDescription("biology course");
        addCourse.setId(2L);
        expectedResult.add(addCourse);
        addCourse = new Course();
        addCourse.setCourseName("physics");
        addCourse.setCourseDescription("physics course");
        addCourse.setId(3L);
        expectedResult.add(addCourse);

        courseDao.save(addCourse);
        List<Course> actualResult = courseDao.findAll();

        assertEquals(expectedResult,
                actualResult,
                "Execute method save from courses");

    }

    @Test
    void saveCourseList_ShouldSaveCoursesAndThenFindAllReturnListOfCoursesWithAddedCourses_WhenCalled() {
        List<Course> addedCourses = new ArrayList<>();

        Course addCourse = new Course();
        addCourse.setCourseName("mathematics");
        addCourse.setCourseDescription("mathematics course");
        addCourse.setId(1L);
        expectedResult.add(addCourse);
        addCourse = new Course();
        addCourse.setCourseName("biology");
        addCourse.setCourseDescription("biology course");
        addCourse.setId(2L);
        expectedResult.add(addCourse);
        addCourse = new Course();
        addCourse.setCourseName("chemical");
        addCourse.setCourseDescription("chemical course");
        addCourse.setId(3L);
        expectedResult.add(addCourse);
        addedCourses.add(addCourse);
        addCourse = new Course();
        addCourse.setCourseName("philosophy");
        addCourse.setCourseDescription("philosophy course");
        addCourse.setId(4L);
        expectedResult.add(addCourse);
        addedCourses.add(addCourse);

        courseDao.saveCourseList(addedCourses);
        List<Course> actualResult = courseDao.findAll();

        assertEquals(expectedResult,
                actualResult,
                "Execute method saveCourseList from courses");
    }

    @Test
    void update_ShouldUpdateSelectedCourseAndThenFindAllReturnListOfCoursesWithUpdatedCourse_WhenCalled() {
        Course updateCourse = new Course();
        updateCourse.setCourseName("literature");
        updateCourse.setCourseDescription("literature course");
        updateCourse.setId(1L);

        Course addCourse = new Course();
        addCourse.setCourseName("biology");
        addCourse.setCourseDescription("biology course");
        addCourse.setId(2L);
        expectedResult.add(addCourse);
        addCourse = new Course();
        addCourse.setCourseName("literature");
        addCourse.setCourseDescription("literature course");
        addCourse.setId(1L);

        expectedResult.add(addCourse);
        courseDao.update(updateCourse);
        List<Course> actualResult = courseDao.findAll();

        assertEquals(expectedResult,
                actualResult,
                "Execute method update from courses");
    }

    @Test
    void delete_ShouldDeleteSelectedCourseAndThenFindAllReturnListOfCoursesWithoutDeletedCourse_WhenCalled() {
        Course deleteCourse = new Course();
        deleteCourse.setCourseName("literature");
        deleteCourse.setCourseDescription("literature course");
        deleteCourse.setId(1L);

        Course addCourse = new Course();
        addCourse.setCourseName("biology");
        addCourse.setCourseDescription("biology course");
        addCourse.setId(2L);
        expectedResult.add(addCourse);

        courseDao.delete(deleteCourse);
        List<Course> actualResult = courseDao.findAll();

        assertEquals(expectedResult,
                actualResult,
                "Execute method delete from courses");
    }

    @Test
    void deleteAll_ShouldDeleteAllCoursesAndThenFindAllReturnEmptyListOfCourses_WhenCalled() {

        courseDao.deleteAll();
        List<Course> actualResult = courseDao.findAll();

        assertEquals(expectedResult.size(),
                actualResult.size(),
                "Execute method deleteAll from courses");
    }
}
