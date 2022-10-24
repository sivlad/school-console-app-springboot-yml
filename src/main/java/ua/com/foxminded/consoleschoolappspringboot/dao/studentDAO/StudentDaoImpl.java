package ua.com.foxminded.consoleschoolappspringboot.dao.studentDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.consoleschoolappspringboot.AppStarter;
import ua.com.foxminded.consoleschoolappspringboot.model.Student;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao{

    private static final String SAVE_STUDENT = "INSERT INTO students(group_id,first_name,last_name) VALUES(?,?,?)";
    private static final String UPDATE_STUDENT = "UPDATE students SET first_name=?,last_name=? WHERE id = ?";
    private static final String FIND_ALL_STUDENTS = "SELECT * FROM students";
    private static final String FIND_ALL_STUDENTS_FROM_COURSE = "SELECT students.id, students.group_id, students.first_name, " +
            " students.last_name FROM students JOIN studentsandcourses ON students.id = studentsandcourses.student_id " +
            " JOIN courses ON courses.id = studentsandcourses.course_id WHERE courses.course_name = ? ";
    private static final String DELETE_ALL_STUDENTS = "DELETE FROM students";
    private static final String DELETE_STUDENT = "DELETE FROM students WHERE students.id = ?";

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    private final RowMapper<Student> studentRowMapper = new StudentRowMapper();

    @Override
    public void save(Student student){
        try {
            jdbcTemplate.update(SAVE_STUDENT, student.getGroupId(), student.getFirstName(),student.getLastName());
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
   }

    @Override
    public int[] saveStudentsList(List<Student> students) {

        return jdbcTemplate.batchUpdate(SAVE_STUDENT,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, students.get(i).getGroupId());
                        ps.setString(2, students.get(i).getFirstName());
                        ps.setString(3, students.get(i).getLastName());
                    }
                    @Override
                    public int getBatchSize() {
                        return students.size();
                    }
                });
    }

    @Override
    public void update(Student student) {
        try {
            jdbcTemplate.update(UPDATE_STUDENT, student.getGroupId(), student.getFirstName(),student.getLastName());
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public void delete(long studentId) {
        try {
            jdbcTemplate.update(DELETE_STUDENT, studentId);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public List<Student> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_STUDENTS, studentRowMapper);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Student> findAllFromCourse(String courseName) {
        try {
            return jdbcTemplate.query(FIND_ALL_STUDENTS_FROM_COURSE, studentRowMapper, courseName);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_STUDENTS);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
