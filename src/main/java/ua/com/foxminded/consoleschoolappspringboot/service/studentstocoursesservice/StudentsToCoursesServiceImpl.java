package ua.com.foxminded.consoleschoolappspringboot.service.studentstocoursesservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentandcourseDAO.StudentsToCoursesRepository;
import ua.com.foxminded.consoleschoolappspringboot.model.StudentsToCourse;

@Service
public class StudentsToCoursesServiceImpl implements StudentsToCoursesService{

    @Autowired
    StudentsToCoursesRepository studentsToCoursesRepository;

    @Override
    public void save(StudentsToCourse studentsToCourse) {
        studentsToCoursesRepository.save(studentsToCourse);
    }

    @Override
    public void assignStudentToCourse(long studentId, String coureName) {
        studentsToCoursesRepository.assignStudentToCourse(studentId,coureName);
    }

    @Override
    public void deleteCourseFromStudent(long studentId, String coureName) {
        studentsToCoursesRepository.deleteCourseFromStudent(studentId,coureName);
    }

    @Override
    public void deleteAll() {
        studentsToCoursesRepository.deleteAll();
    }
}
