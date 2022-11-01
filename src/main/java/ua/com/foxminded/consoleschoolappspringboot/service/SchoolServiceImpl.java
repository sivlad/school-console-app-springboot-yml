package ua.com.foxminded.consoleschoolappspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO.GroupDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentDAO.StudentDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentandcourseDAO.StudentsToCoursesDaoImpl;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;
import ua.com.foxminded.consoleschoolappspringboot.model.Student;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private StudentsToCoursesDaoImpl studentsToCoursesDao;

    @Override
    public List<String> findAllGroupsWithLessOrEqualsStudentCount(int numberStudents) {
        return groupDao.findAllGroupsWithLessOreEqualStudentsNumber(numberStudents);
    }

    @Override
    public List<Student> findAllStudentsRelatedToTheCourseWithTheGivenName(String courseName) {
        return studentDao.findAllFromCourse(courseName);
    }

    @Override
    public void addNewStudent(String groupName, String firstName, String lastName) {
        studentDao.save(groupName,firstName,lastName);
    }

    @Override
    public void deleteStudent(long studentId) {
        studentDao.delete(studentId);
    }

    @Override
    public void addStudentToTheCourse(long studentId, String courseName) {
        studentsToCoursesDao.assignStudentToCourse(studentId, courseName);
    }

    @Override
    public void removeStudentFromCourse(long studentId, String courseName) {
        studentsToCoursesDao.deleteCourseFromStudent(studentId, courseName);
    }
}
