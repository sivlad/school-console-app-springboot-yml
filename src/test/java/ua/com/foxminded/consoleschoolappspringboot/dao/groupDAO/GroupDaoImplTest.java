package ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.consoleschoolappspringboot.AppStarter;
import ua.com.foxminded.consoleschoolappspringboot.ConsoleSchoolAppSpringbootApplication;
import ua.com.foxminded.consoleschoolappspringboot.TestConteinersConfiguration;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts = {"classpath:schema.sql", "classpath:test-data.sql"})
@ContextConfiguration(classes = ConsoleSchoolAppSpringbootApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
public class GroupDaoImplTest {

    @Autowired
    private TestConteinersConfiguration testConteinersConfiguration;

    @MockBean
    private AppStarter appStarter;

    @Autowired
    private GroupDao groupDao;

    @Order(1)
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

    @Order(2)
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

    @Order(3)
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

    @Order(4)
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

    @Order(5)
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

    @Order(6)
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
