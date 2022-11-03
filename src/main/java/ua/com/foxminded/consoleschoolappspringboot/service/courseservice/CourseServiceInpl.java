package ua.com.foxminded.consoleschoolappspringboot.service.courseservice;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.consoleschoolappspringboot.dao.courseDAO.CourseDao;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;

import java.util.List;

public class CourseServiceInpl implements CourseService{

    @Autowired
    CourseDao courseDao;

    @Override
    public void save(Course course) {
        courseDao.save(course);
    }

    @Override
    public int[] saveCourseList(List<Course> courses) {
        return courseDao.saveCourseList(courses);
    }

    @Override
    public void update(Course course) {
        courseDao.update(course);
    }

    @Override
    public void delete(Course course) {
        courseDao.delete(course);
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public List<Course> findAllCoursesFromStudent(long studentId) {
        return courseDao.findAllCoursesFromStudent(studentId);
    }

    @Override
    public void deleteAll() {
        courseDao.deleteAll();
    }
}
