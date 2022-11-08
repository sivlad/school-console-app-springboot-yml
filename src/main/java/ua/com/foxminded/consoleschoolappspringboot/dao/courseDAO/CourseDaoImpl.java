package ua.com.foxminded.consoleschoolappspringboot.dao.courseDAO;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
public class CourseDaoImpl implements CourseDao {

    private static final String SAVE_COURSE = "INSERT INTO courses(course_name, course_description) VALUES(?,?)";
    private static final String FIND_ALL_COURSES = "SELECT * FROM courses";
    private static final String FIND_ALL_COURSES_FROM_STUDENT = "SELECT courses.id, courses.course_name, courses.course_description\n" +
            "FROM courses JOIN studentsandcourses ON courses.id = studentsandcourses.course_id\n" +
            "JOIN students ON studentsandcourses.student_id = students.id\n" +
            "WHERE students.id = ? ";
    private static final String DELETE_ALL_COURSES = "DELETE FROM courses";
    private static final String UPDATE_COURSE = "UPDATE courses SET course_name = ?, course_description = ? " +
            " WHERE courses.id = ? ";
    private static final String DELETE_COURSE = "DELETE FROM courses WHERE courses.id = ?";

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    private final RowMapper<Course> courseRowMapper = new CourseRowMapper();

    @Override
    public void save(Course course) {
        try {
            log.debug("Save course");
            jdbcTemplate.update(SAVE_COURSE, course.getCourseName(), course.getCourseDescription());
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Override
    public int[] saveCourseList(List<Course> courses) {

        log.debug("Save list of courses");
        return jdbcTemplate.batchUpdate(SAVE_COURSE,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, courses.get(i).getCourseName());
                        ps.setString(2, courses.get(i).getCourseDescription());
                    }
                    @Override
                    public int getBatchSize() {
                        return courses.size();
                    }
                });
    }

    @Override
    public void update(Course course) {
        try {
            log.debug("Update course");
            jdbcTemplate.update(UPDATE_COURSE, course.getCourseName(), course.getCourseDescription(), course.getId());
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Override
    public void delete(Course course) {
        try {
            log.debug("Delete course");
            jdbcTemplate.update(DELETE_COURSE, course.getId());
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<Course> findAll() {
        try {
            log.debug("Find all courses");
            return jdbcTemplate.query(FIND_ALL_COURSES, courseRowMapper);
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Course> findAllCoursesFromStudent(long studentId) {
        try {
            log.debug("Fina all courses from student");
            return jdbcTemplate.query(FIND_ALL_COURSES_FROM_STUDENT, courseRowMapper, studentId);
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            log.debug("Delete all courses");
            jdbcTemplate.update(DELETE_ALL_COURSES);
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }
}
