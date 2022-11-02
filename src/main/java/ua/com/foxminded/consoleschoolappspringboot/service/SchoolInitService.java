package ua.com.foxminded.consoleschoolappspringboot.service;

import ua.com.foxminded.consoleschoolappspringboot.model.Course;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;
import ua.com.foxminded.consoleschoolappspringboot.model.Student;
import ua.com.foxminded.consoleschoolappspringboot.model.StudentsToCourse;

import java.util.List;

public interface SchoolInitService {

    List<Course> findAllCourses();

    List<Student> findAllStudents();

    List<Group> finaAllGroups();

    void deleteAllRowsInDB();

    void saveGroupList(List<Group> groups);

    void saveCourseList(List<Course> courses);

    void saveStudentsList(List<Student> students);

    void assignStudentsToCourse(StudentsToCourse studentsToCourse);

    List<Course> findAllCoursesFromStudent(long studentId);
}
