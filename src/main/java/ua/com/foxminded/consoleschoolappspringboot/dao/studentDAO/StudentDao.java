package ua.com.foxminded.consoleschoolappspringboot.dao.studentDAO;

import ua.com.foxminded.consoleschoolappspringboot.model.Student;

import java.util.List;

public interface StudentDao {

    void save(Student student) ;

    int[] saveStudentsList(List<Student> students) ;

    void update(Student student) ;

    void delete(long studentId) ;

    List<Student> findAll() ;

    List<Student> findAllFromCourse(String courseName) ;

    void deleteAll() ;
}
