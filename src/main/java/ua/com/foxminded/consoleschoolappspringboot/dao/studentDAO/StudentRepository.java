package ua.com.foxminded.consoleschoolappspringboot.dao.studentDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.foxminded.consoleschoolappspringboot.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    String SAVE_STUDENT = "INSERT INTO students(group_id,first_name,last_name) " +
            " VALUES((SELECT groups.id FROM groups WHERE group_name = :groupName),:firstName,:lastName)";
    String FIND_ALL_STUDENTS_FROM_COURSE = "SELECT students.id, students.group_id, students.first_name, " +
            " students.last_name FROM students JOIN studentsandcourses ON students.id = studentsandcourses.student_id " +
            " JOIN courses ON courses.id = studentsandcourses.course_id WHERE courses.course_name = :courseName ";
    String DELETE_STUDENT = "DELETE FROM students WHERE students.id = :studentId";

    @Query(value = SAVE_STUDENT, nativeQuery = true)
    void saveStudentByStringParametres(@Param("groupName") String groupName, @Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query(value = FIND_ALL_STUDENTS_FROM_COURSE, nativeQuery = true)
    List<Student> findAllFromCourse(@Param("courseName") String courseName);

    @Query(value = DELETE_STUDENT, nativeQuery = true)
    void delete(@Param("studentId") long studentId) ;
}
