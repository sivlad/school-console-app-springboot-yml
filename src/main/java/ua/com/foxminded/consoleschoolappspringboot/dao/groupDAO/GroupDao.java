package ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO;

import ua.com.foxminded.consoleschoolappspringboot.model.Group;

import java.util.List;

public interface GroupDao {
    void save(Group group);

    int[] saveGroupList(List<Group> groups);

    void update(Group group);

    void delete(Group group);

    List<Group> findAll();

    void deleteAll();

    List<String> findAllGroupsWithLessOreEqualStudentsNumber(int numberStudents);
}
