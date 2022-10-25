package ua.com.foxminded.consoleschoolappspringboot.dao.courseDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.consoleschoolappspringboot.AppStarter;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    private final RowMapper<Course> courseRowMapper = new CourseRowMapper();

    @Override
    public void save(Course course) {
        try {
            jdbcTemplate.update(SAVE_COURSE, course.getCourseName(), course.getCourseDescription());
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public int[] saveCourseList(List<Course> courses) {

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
            jdbcTemplate.update(UPDATE_COURSE, course.getCourseName(), course.getCourseDescription(),course.getId());
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public void delete(Course course) {
        try {
            jdbcTemplate.update(DELETE_COURSE, course.getId());
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<Course> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_COURSES, courseRowMapper);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Course> findAllCoursesFromStudent(long studentId) {
        try {
            return jdbcTemplate.query(FIND_ALL_COURSES_FROM_STUDENT, courseRowMapper, studentId);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_COURSES);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
