package ua.com.foxminded.consoleschoolappspringboot.dao.studentandcourseDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.foxminded.consoleschoolappspringboot.model.StudentsToCourse;

public interface StudentsToCoursesRepository extends JpaRepository<StudentsToCourse, Long> {

    String ASSIGN_STUDENT_TO_COURSE = "INSERT INTO studentsandcourses(student_id,course_id) " +
            " VALUES(:studentId, (SELECT courses.id FROM courses WHERE courses.course_name = :courseName))";
    String DELETE_COURSE_FROM_STUDENT = "DELETE FROM studentsandcourses " +
            " WHERE  studentsandcourses.student_id = :studentId AND " +
            " studentsandcourses.course_id = (SELECT courses.id FROM courses WHERE courses.course_name = :courseName) ";

    @Query(value = ASSIGN_STUDENT_TO_COURSE, nativeQuery = true)
    void assignStudentToCourse(@Param("studentId") long studentId,@Param("courseName") String courseName);

    @Query(value = DELETE_COURSE_FROM_STUDENT, nativeQuery = true)
    void deleteCourseFromStudent(@Param("studentId") long studentId,@Param("courseName") String courseName);

}
