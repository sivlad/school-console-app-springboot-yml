package ua.com.foxminded.consoleschoolappspringboot.dao.courseDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    String FIND_ALL_COURSES_FROM_STUDENT = """
            SELECT courses_.*
            FROM Course courses_ JOIN StudentsToCourse studentsandcourses_ ON courses_.id = studentsandcourses_.courseId
            JOIN Student students_ ON studentsandcourses_.studentId = students_.id
            WHERE students_.id = :studentId\s""";

    @Query(value = FIND_ALL_COURSES_FROM_STUDENT, nativeQuery = true)
    List<Course> findAllCoursesFromStudent(@Param("studentId") long count) throws SQLException;
}