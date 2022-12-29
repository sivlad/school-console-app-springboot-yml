package ua.com.foxminded.consoleschoolappspringboot.service.courseservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.consoleschoolappspringboot.dao.courseDAO.CourseRepository;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public int[] saveCourseList(List<Course> courses) {
        courseRepository.saveAll(courses);
        return new int[1];
    }

    @Override
    public void update(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void delete(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findAllCoursesFromStudent(long studentId) {
        try {
            return courseRepository.findAllCoursesFromStudent(studentId);
        } catch (SQLException e) {
            log.warn("Bad users input" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        courseRepository.deleteAll();
    }
}
