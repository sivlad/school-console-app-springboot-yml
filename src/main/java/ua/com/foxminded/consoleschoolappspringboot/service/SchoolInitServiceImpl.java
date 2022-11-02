package ua.com.foxminded.consoleschoolappspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.consoleschoolappspringboot.dao.courseDAO.CourseDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO.GroupDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentDAO.StudentDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentandcourseDAO.StudentsToCoursesDaoImpl;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;
import ua.com.foxminded.consoleschoolappspringboot.model.Student;
import ua.com.foxminded.consoleschoolappspringboot.model.StudentsToCourse;

import java.util.List;

@Service
public class SchoolInitServiceImpl implements SchoolInitService{

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private StudentsToCoursesDaoImpl studentsToCoursesDao;

    @Override
    public List<Course> findAllCourses() {
        return courseDao.findAll();
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public List<Group> finaAllGroups() {
        return groupDao.findAll();
    }

    @Override
    public void deleteAllRowsInDB() {
        studentsToCoursesDao.deleteAll();
        courseDao.deleteAll();
        studentDao.deleteAll();
        groupDao.deleteAll();
    }

    @Override
    public void saveGroupList(List<Group> groups) {
        groupDao.saveGroupList(groups);
    }

    @Override
    public void saveCourseList(List<Course> courses) {
        courseDao.saveCourseList(courses);
    }

    @Override
    public void saveStudentsList(List<Student> students) {
        studentDao.saveStudentsList(students);
    }

    @Override
    public void assignStudentsToCourse(StudentsToCourse studentsToCourse) {
        studentsToCoursesDao.save(studentsToCourse);
    }

    @Override
    public List<Course> findAllCoursesFromStudent(long studentId) {
        return courseDao.findAllCoursesFromStudent(studentId);
    }
}
