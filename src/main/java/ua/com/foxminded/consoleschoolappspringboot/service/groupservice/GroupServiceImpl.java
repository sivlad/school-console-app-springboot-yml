package ua.com.foxminded.consoleschoolappspringboot.service.groupservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO.GroupRepository;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;
import ua.com.foxminded.consoleschoolappspringboot.model.GroupsAndCounts;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    GroupRepository groupRepository;

    @Override
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Override
    public int[] saveGroupList(List<Group> groups) {
        groupRepository.saveAll(groups);
        return new int[1];
    }

    @Override
    public void update(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void delete(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public void deleteAll() {
        groupRepository.deleteAll();
    }

    @Override
    public List<String> findAllGroupsWithLessOreEqualStudentsNumber(int numberStudents) {
        try {
            List<GroupsAndCounts> groups = groupRepository.findAllCoursesFromStudent(numberStudents);
            List<String> resStringList = new ArrayList<>();
            for (var current : groups) {
                resStringList.add(current.getGroupName());
            }
            return resStringList;
        } catch (SQLException e) {
            log.warn("Bad sql queue" + e.getMessage());
            return new ArrayList<>();
        }
    }
}
