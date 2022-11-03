package ua.com.foxminded.consoleschoolappspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO.GroupDao;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;

import java.util.List;

public class GroupServiceImpl implements GroupService{

    @Autowired
    GroupDao groupDao;

   @Override
    public void save(Group group) {
       groupDao.save(group);
    }

    @Override
    public int[] saveGroupList(List<Group> groups) {
        return groupDao.saveGroupList(groups);
    }

    @Override
    public void update(Group group) {
        groupDao.update(group);
    }

    @Override
    public void delete(Group group) {
        groupDao.delete(group);
    }

    @Override
    public List<Group> findAll() {
        return groupDao.findAll();
    }

    @Override
    public void deleteAll() {
        groupDao.deleteAll();
    }

    @Override
    public List<String> findAllGroupsWithLessOreEqualStudentsNumber(int numberStudents) {
        return groupDao.findAllGroupsWithLessOreEqualStudentsNumber(numberStudents);
    }
}
