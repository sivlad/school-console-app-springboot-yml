package ua.com.foxminded.consoleschoolappspringboot.service.studentstocoursesservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentandcourseDAO.StudentsToCoursesDao;
import ua.com.foxminded.consoleschoolappspringboot.model.StudentsToCourse;

@Service
public class StudentsToCoursesServiceImpl implements StudentsToCoursesService{

    @Autowired
    StudentsToCoursesDao studentsToCoursesDao;

    @Override
    public void save(StudentsToCourse studentsToCourse) {
        studentsToCoursesDao.save(studentsToCourse);
    }

    @Override
    public void assignStudentToCourse(long studentId, String coureName) {
        studentsToCoursesDao.assignStudentToCourse(studentId,coureName);
    }

    @Override
    public void deleteCourseFromStudent(long studentId, String coureName) {
        studentsToCoursesDao.deleteCourseFromStudent(studentId,coureName);
    }

    @Override
    public void deleteAll() {
        studentsToCoursesDao.deleteAll();
    }
}
