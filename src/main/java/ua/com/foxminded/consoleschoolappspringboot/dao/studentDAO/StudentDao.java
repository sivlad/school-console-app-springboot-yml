package ua.com.foxminded.consoleschoolappspringboot.dao.studentDAO;

import ua.com.foxminded.consoleschoolappspringboot.model.Student;

import java.util.List;

public interface StudentDao {

    void save(String groupName, String firstName, String lastName) ;

    void save(Student student) ;

    int[] saveStudentsList(List<Student> students) ;

    void update(Student student) ;

    void delete(long studentId) ;

    List<Student> findAll() ;

    List<Student> findAllFromCourse(String courseName) ;

    void deleteAll() ;
}
