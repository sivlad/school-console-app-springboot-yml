package ua.com.foxminded.consoleschoolappspringboot.service.courseservice;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.consoleschoolappspringboot.dao.courseDAO.CourseDao;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;

import java.util.List;

public interface CourseService {

    void save(Course course);

    int[] saveCourseList(List<Course> courses) ;

    void update(Course course);

    void delete(Course course);

    List<Course> findAll();

    List<Course> findAllCoursesFromStudent(long studentId);

    void deleteAll();
}
