package ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
public class GroupDaoImpl implements GroupDao{

    private static final String SAVE_GROUP = "INSERT INTO groups(group_name) VALUES(?)";
    private static final String FIND_ALL_GROUPS = "SELECT * FROM groups";
    private static final String DELETE_ALL_GROUPS = "DELETE FROM groups";
    private static final String FIND_ALL_GROUPS_WITH_LESS_ORE_EQUAL_STUDENTS =
            "SELECT groups.group_name, COUNT(*) AS count_students\n" +
                    "FROM groups JOIN students ON groups.id = students.group_id\n" +
                    "GROUP BY groups.group_name\n" +
                    "HAVING COUNT(*) <= ? ";
    private static final String UPDATE_GROUP = "UPDATE groups SET group_name = ? " +
            " WHERE groups.id = ? ";
    private static final String DELETE_GROUP = "DELETE FROM groups WHERE groups.id = ?";

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    private final RowMapper<Group> groupRowMapper = new GroupRowMapper();
    private final RowMapper<String> groupNameRowMapper = new GroupNameRowMapper();

    @Override
    public void save(Group group) {
        try {
            log.debug("Save group");
            jdbcTemplate.update(SAVE_GROUP, group.getGroupName());
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Override
    public int[] saveGroupList(List<Group> groups) {
        log.debug("Add list of groups");
        return jdbcTemplate.batchUpdate(SAVE_GROUP,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, groups.get(i).getGroupName());
                    }
                    @Override
                    public int getBatchSize() {
                        return groups.size();
                    }
                });
    }

    @Override
    public void update(Group group) {
        try {
            log.debug("Update group");
            jdbcTemplate.update(UPDATE_GROUP, group.getGroupName(),group.getId());
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Override
    public void delete(Group group) {

        try {
            log.debug("Delete group");
            jdbcTemplate.update(DELETE_GROUP, group.getId());
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Override
    public List<Group> findAll() {
        try {
            log.debug("Find all groups");
            return jdbcTemplate.query(FIND_ALL_GROUPS, groupRowMapper);
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            log.debug("Delete all groups");
            jdbcTemplate.update(DELETE_ALL_GROUPS);
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Override
    public List<String> findAllGroupsWithLessOreEqualStudentsNumber(int numberStudents) {

        try {
            log.debug("Find all groups with less ore equal number of students");
            return jdbcTemplate.query(FIND_ALL_GROUPS_WITH_LESS_ORE_EQUAL_STUDENTS, groupNameRowMapper, numberStudents);
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
            return new ArrayList<>();
        }
    }
}
