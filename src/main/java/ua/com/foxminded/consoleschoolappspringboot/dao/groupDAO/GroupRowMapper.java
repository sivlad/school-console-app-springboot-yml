package ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO;

import org.springframework.jdbc.core.RowMapper;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupRowMapper implements RowMapper<Group> {
    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group();
        group.setId(rs.getLong("id"));
        group.setGroupName(rs.getString("group_name"));
        return group;
    }
}
