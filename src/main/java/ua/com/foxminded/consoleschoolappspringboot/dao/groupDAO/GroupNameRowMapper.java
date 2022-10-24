package ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupNameRowMapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        String groupName = rs.getString("group_name");
        return groupName;
    }
}
