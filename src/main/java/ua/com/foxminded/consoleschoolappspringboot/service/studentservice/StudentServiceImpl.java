package ua.com.foxminded.consoleschoolappspringboot.service.studentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentDAO.StudentRepository;
import ua.com.foxminded.consoleschoolappspringboot.model.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public void save(String groupName, String firstName, String lastName) {
        studentRepository.saveStudentByStringParametres(groupName, firstName, lastName);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public int[] saveStudentsList(List<Student> students) {
        studentRepository.saveAll(students);
        return new int[1];
    }

    @Override
    public void update(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(long studentId) {
        studentRepository.delete(studentId);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findAllFromCourse(String courseName) {
        return studentRepository.findAllFromCourse(courseName);
    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();
    }
}
