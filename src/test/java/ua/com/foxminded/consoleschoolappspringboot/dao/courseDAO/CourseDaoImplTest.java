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

    @Autowired
    CourseDao courseDao;

    @Test
    void findAll_ShouldReturnListOfCourses_WhenCalled() {

        List<Course> expectedResult = new ArrayList<>();
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


}
