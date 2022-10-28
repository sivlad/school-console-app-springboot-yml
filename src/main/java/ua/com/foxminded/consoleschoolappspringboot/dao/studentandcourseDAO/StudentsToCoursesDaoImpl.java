package ua.com.foxminded.consoleschoolappspringboot.dao.studentandcourseDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.consoleschoolappspringboot.AppStarter;
import ua.com.foxminded.consoleschoolappspringboot.model.StudentsToCourse;

@Repository
public class StudentsToCoursesDaoImpl implements StudentsToCoursesDao{

    private static final String SAVE_STUDENT_TO_COURSE = "INSERT INTO studentsandcourses(student_id,course_id) VALUES(?,?)";
    private static final String DELETE_ALL_STUDENT_TO_COURSE = "DELETE FROM studentsandcourses";
    private static final String ASSIGN_STUDENT_TO_COURSE = "INSERT INTO studentsandcourses(student_id,course_id) " +
                " VALUES(?, (SELECT courses.id FROM courses WHERE courses.course_name = ?))";
    private static final String DELETE_COURSE_FROM_STUDENT = "DELETE FROM studentsandcourses " +
            " WHERE  studentsandcourses.student_id = ? AND " +
            " studentsandcourses.course_id = (SELECT courses.id FROM courses WHERE courses.course_name = ?) ";

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    @Override
    public void save(StudentsToCourse studentsToCourse) {

        try {
            jdbcTemplate.update(SAVE_STUDENT_TO_COURSE, studentsToCourse.getStudentId(), studentsToCourse.getCourseId());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void assignStudentToCourse(long studentId, String courseName) {

        try {
            jdbcTemplate.update(ASSIGN_STUDENT_TO_COURSE,studentId,courseName);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteCourseFromStudent(long studentId, String courseName) {

        try {
            jdbcTemplate.update(DELETE_COURSE_FROM_STUDENT,studentId,courseName);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_STUDENT_TO_COURSE);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
