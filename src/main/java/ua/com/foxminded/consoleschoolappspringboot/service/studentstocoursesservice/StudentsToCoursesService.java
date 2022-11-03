package ua.com.foxminded.consoleschoolappspringboot.service.studentstocoursesservice;

import ua.com.foxminded.consoleschoolappspringboot.model.StudentsToCourse;

public interface StudentsToCoursesService {
    void save(StudentsToCourse studentsToCourse) ;

    void assignStudentToCourse(long studentId, String coureName);

    void deleteCourseFromStudent(long studentId, String coureName);

    void deleteAll() ;
}
