package ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql({"/schema.sql", "/test-data.sql"})
public class GroupDaoImplTest {

    @Autowired
    GroupDao groupDao;

    @Test
    void findAll_ShouldReturnListOfGroups_WhenCalled() {
        List<Group> expectedResult = new ArrayList<>();
        Group addGroup = new Group();
        addGroup.setGroupName("rr-33");
        addGroup.setId(1L);
        expectedResult.add(addGroup);
        addGroup = new Group();
        addGroup.setGroupName("op-04");
        addGroup.setId(2L);
        expectedResult.add(addGroup);
        addGroup = new Group();
        addGroup.setGroupName("qs-56");
        addGroup.setId(3L);
        expectedResult.add(addGroup);

        List<Group> actualResult = groupDao.findAll();

        assertEquals(expectedResult,
                actualResult,
                "Execute method findAll from groups");
    }

    @Test
    void save_ShouldSaveGroupAndThenFindAllReturnListOfGroupsWithAddedGroup_WhenCalled() {
        List<Group> expectedResult = new ArrayList<>();
        Group addGroup = new Group();
        addGroup.setGroupName("rr-33");
        addGroup.setId(1L);
        expectedResult.add(addGroup);
        addGroup = new Group();
        addGroup.setGroupName("op-04");
        addGroup.setId(2L);
        expectedResult.add(addGroup);
        addGroup = new Group();
        addGroup.setGroupName("qs-56");
        addGroup.setId(3L);
        expectedResult.add(addGroup);
        addGroup = new Group();
        addGroup.setGroupName("uu-78");
        addGroup.setId(4L);
        expectedResult.add(addGroup);

        groupDao.save(addGroup);
        List<Group> actualResult = groupDao.findAll();

        assertEquals(expectedResult,
                actualResult,
                "Execute method save from groups");
    }

    @Test
    void saveCourseList_ShouldSaveCoursesAndThenFindAllReturnListOfCoursesWithAddedCourses_WhenCalled() {
        List<Group> addedGroup = new ArrayList<>();

        List<Group> expectedResult = new ArrayList<>();
        Group addGroup = new Group();
        addGroup.setGroupName("rr-33");
        addGroup.setId(1L);
        expectedResult.add(addGroup);
        addGroup = new Group();
        addGroup.setGroupName("op-04");
        addGroup.setId(2L);
        expectedResult.add(addGroup);
        addGroup = new Group();
        addGroup.setGroupName("qs-56");
        addGroup.setId(3L);
        expectedResult.add(addGroup);
        addGroup = new Group();
        addGroup.setGroupName("uu-78");
        addGroup.setId(4L);
        expectedResult.add(addGroup);
        addedGroup.add(addGroup);
        addGroup = new Group();
        addGroup.setGroupName("pi-32");
        addGroup.setId(5L);
        expectedResult.add(addGroup);
        addedGroup.add(addGroup);

        groupDao.saveGroupList(addedGroup);
        List<Group> actualResult = groupDao.findAll();

        assertEquals(expectedResult,
                actualResult,
                "Execute method saveCourseList from groups");
    }

    @Test
    void update_ShouldUpdateSelectedGroupAndThenFindAllReturnListOfGroupsWithUpdatedGroup_WhenCalled() {

        Group updateGroup = new Group();
        updateGroup.setGroupName("rr-55");
        updateGroup.setId(1L);

        List<Group> expectedResult = new ArrayList<>();
        Group addGroup = new Group();
        addGroup.setGroupName("op-04");
        addGroup.setId(2L);
        expectedResult.add(addGroup);
        addGroup = new Group();
        addGroup.setGroupName("qs-56");
        addGroup.setId(3L);
        expectedResult.add(addGroup);
        addGroup = new Group();
        addGroup.setGroupName("rr-55");
        addGroup.setId(1L);
        expectedResult.add(addGroup);

        groupDao.update(updateGroup);
        List<Group> actualResult = groupDao.findAll();

        assertEquals(expectedResult,
                actualResult,
                "Execute method update from groups");
    }

    @Test
    void delete_ShouldDeleteSelectedGroupAndThenFindAllReturnListOfGroupsWithoutDeletedGroup_WhenCalled() {

        Group deleteGroup = new Group();
        deleteGroup.setGroupName("rr-33");
        deleteGroup.setId(1L);

        List<Group> expectedResult = new ArrayList<>();
        Group addGroup = new Group();
        addGroup.setGroupName("op-04");
        addGroup.setId(2L);
        expectedResult.add(addGroup);
        addGroup = new Group();
        addGroup.setGroupName("qs-56");
        addGroup.setId(3L);
        expectedResult.add(addGroup);

        groupDao.delete(deleteGroup);
        List<Group> actualResult = groupDao.findAll();

        assertEquals(expectedResult,
                actualResult,
                "Execute method delete from groups");
    }

    @Test
    void deleteAll_ShouldDeleteAllGroupsAndThenFindAllReturnEmptyListOfGroups_WhenCalled() {

        groupDao.deleteAll();
        List<Group> expectedResult = new ArrayList<>();
        List<Group> actualResult = groupDao.findAll();

        assertEquals(expectedResult,
                actualResult,
                "Execute method deleteAll from groups");
    }
}
