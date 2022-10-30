package ua.com.foxminded.consoleschoolappspringboot.dao.courseDAO;

import ua.com.foxminded.consoleschoolappspringboot.model.Course;

import java.util.List;

public interface CourseDao {

   void save(Course course);

   int[] saveCourseList(List<Course> courses) ;

   void update(Course course);

   void delete(Course course);

   List<Course> findAll();

   List<Course> findAllCoursesFromStudent(long studentId);

   void deleteAll();
}
