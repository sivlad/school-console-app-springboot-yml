package ua.com.foxminded.consoleschoolappspringboot.dao.studentDAO;

import org.springframework.jdbc.core.RowMapper;
import ua.com.foxminded.consoleschoolappspringboot.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setGroupId(rs.getLong("group_id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        return student;
    }

}
