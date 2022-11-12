package ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
public class GroupDaoImpl implements GroupDao{

    private static final String SAVE_GROUP = "INSERT INTO groups(group_name) VALUES(:groupName)";
    private static final String FIND_ALL_GROUPS = "SELECT * FROM groups";
    private static final String DELETE_ALL_GROUPS = "DELETE FROM groups";
    private static final String FIND_ALL_GROUPS_WITH_LESS_ORE_EQUAL_STUDENTS =
            "SELECT groups.group_name, COUNT(*) AS count_students\n" +
                    "FROM groups JOIN students ON groups.id = students.group_id\n" +
                    "GROUP BY groups.group_name\n" +
                    "HAVING COUNT(*) <= :numberOfStudents ";
    private static final String UPDATE_GROUP = "UPDATE groups SET group_name = :groupName " +
            " WHERE groups.id = :groupId ";
    private static final String DELETE_GROUP = "DELETE FROM groups WHERE groups.id = :groupId";

    @PersistenceContext
    private EntityManager entityManager;

    private final RowMapper<Group> groupRowMapper = new GroupRowMapper();
    private final RowMapper<String> groupNameRowMapper = new GroupNameRowMapper();

    @Transactional
    @Override
    public void save(Group group) {
        try {
            log.debug("Save group");
            entityManager.createNativeQuery(SAVE_GROUP,Group.class)
                    .setParameter("groupName",group.getGroupName())
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public int[] saveGroupList(List<Group> groups) {
        log.debug("Add list of groups");

        for (Group currentGroup : groups)
        {
            entityManager.createNativeQuery(SAVE_GROUP,Course.class)
                    .setParameter("groupName",currentGroup.getGroupName())
                    .executeUpdate();
        }
        return new int[] {};
    }

    @Transactional
    @Override
    public void update(Group group) {
        try {
            log.debug("Update group");
            entityManager.createNativeQuery(UPDATE_GROUP,Course.class)
                    .setParameter("groupId",group.getId())
                    .setParameter("groupName",group.getGroupName())
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void delete(Group group) {
        try {
            log.debug("Delete group");
            entityManager.createNativeQuery(DELETE_GROUP)
                    .setParameter("groupId",group.getId())
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public List<Group> findAll() {
        try {
            log.info("Find all groups");
            return entityManager.createNativeQuery(FIND_ALL_GROUPS,Group.class).getResultList();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Transactional
    @Override
    public void deleteAll() {
        try {
            log.debug("Delete all groups");
            entityManager.createNativeQuery(DELETE_ALL_GROUPS)
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public List<String> findAllGroupsWithLessOreEqualStudentsNumber(int numberStudents) {

        try {
            log.info("Find all groups with less ore equal number of students");

            List<Object> result = entityManager.createNativeQuery(FIND_ALL_GROUPS_WITH_LESS_ORE_EQUAL_STUDENTS)
                    .setParameter("numberOfStudents", numberStudents)
                    .getResultList();

            List<String> resStringList = new ArrayList<>();

            for (var current : result) {
                resStringList.add(current.toString());
            }
            return resStringList;
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
            return new ArrayList<>();
        }
    }
}
