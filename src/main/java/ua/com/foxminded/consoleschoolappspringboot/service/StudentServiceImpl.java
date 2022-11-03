package ua.com.foxminded.consoleschoolappspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentDAO.StudentDao;
import ua.com.foxminded.consoleschoolappspringboot.model.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentDao studentDao;

    @Override
    public void save(String groupName, String firstName, String lastName) {
        studentDao.save(groupName,firstName,lastName);
    }

    @Override
    public void save(Student student) {
        studentDao.save(student);
    }

    @Override
    public int[] saveStudentsList(List<Student> students) {
        return studentDao.saveStudentsList(students);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void delete(long studentId) {
        studentDao.delete(studentId);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public List<Student> findAllFromCourse(String courseName) {
        return studentDao.findAllFromCourse(courseName);
    }

    @Override
    public void deleteAll() {
        studentDao.deleteAll();
    }
}
