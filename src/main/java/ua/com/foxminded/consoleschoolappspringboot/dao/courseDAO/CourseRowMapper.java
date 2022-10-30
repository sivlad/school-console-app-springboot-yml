package ua.com.foxminded.consoleschoolappspringboot.dao.courseDAO;

import org.springframework.jdbc.core.RowMapper;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {

    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getLong("id"));
        course.setCourseName(rs.getString("course_name"));
        course.setCourseDescription(rs.getString("course_description"));
        return course;
    }
}
