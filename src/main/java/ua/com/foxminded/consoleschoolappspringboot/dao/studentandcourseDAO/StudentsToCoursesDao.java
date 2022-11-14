package ua.com.foxminded.consoleschoolappspringboot.dao.studentandcourseDAO;

import ua.com.foxminded.consoleschoolappspringboot.model.StudentsToCourse;

public interface StudentsToCoursesDao {

    void save(StudentsToCourse studentsToCourse) ;

    void assignStudentToCourse(long studentId, String courseName);

    void deleteCourseFromStudent(long studentId, String courseName);

    void deleteAll() ;
}
