package ua.com.foxminded.consoleschoolappspringboot.service.schoolservice;

import ua.com.foxminded.consoleschoolappspringboot.model.Student;

import java.util.List;

public interface SchoolService {

    List<String> findAllGroupsWithLessOrEqualsStudentCount(int numberStudents);

    List<Student> findAllStudentsRelatedToTheCourseWithTheGivenName(String courseName);

    void addNewStudent(String groupName, String firstName, String lastName);

    void deleteStudent(long studentId);

    void addStudentToTheCourse(long studentId, String courseName);

    void removeStudentFromCourse(long studentId, String courseName);

}
