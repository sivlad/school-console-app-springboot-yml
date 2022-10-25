package ua.com.foxminded.consoleschoolappspringboot.dao.courseDAO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql({"/schema.sql", "/test-data.sql"})
class CourseDaoImplTest {

    private List<Course> expectedResult = new ArrayList<>();


    @Autowired
    CourseDao courseDao;

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
