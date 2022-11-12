package ua.com.foxminded.consoleschoolappspringboot.dao.studentandcourseDAO;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.consoleschoolappspringboot.model.StudentsToCourse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Log4j2
@Repository
public class StudentsToCoursesDaoImpl implements StudentsToCoursesDao{

    private static final String SAVE_STUDENT_TO_COURSE = "INSERT INTO studentsandcourses(student_id,course_id) VALUES(:studentId,:courseId)";
    private static final String DELETE_ALL_STUDENT_TO_COURSE = "DELETE FROM studentsandcourses";
    private static final String ASSIGN_STUDENT_TO_COURSE = "INSERT INTO studentsandcourses(student_id,course_id) " +
                " VALUES(:studentId, (SELECT courses.id FROM courses WHERE courses.course_name = :courseName))";
    private static final String DELETE_COURSE_FROM_STUDENT = "DELETE FROM studentsandcourses " +
            " WHERE  studentsandcourses.student_id = :studentId AND " +
            " studentsandcourses.course_id = (SELECT courses.id FROM courses WHERE courses.course_name = :courseName) ";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(StudentsToCourse studentsToCourse) {

        try {
            log.debug("Save StudentToCourse");
            entityManager.createNativeQuery(SAVE_STUDENT_TO_COURSE, StudentsToCourse.class)
                    .setParameter("studentId",studentsToCourse.getStudentId())
                    .setParameter("courseId",studentsToCourse.getCourseId())
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void assignStudentToCourse(long studentId, String courseName) {

        try {
            log.info("Assign Student to course");
            entityManager.createNativeQuery(ASSIGN_STUDENT_TO_COURSE)
                    .setParameter("studentId",studentId)
                    .setParameter("courseName",courseName)
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void deleteCourseFromStudent(long studentId, String courseName) {

        try {
            log.debug("Delete course from student");
            entityManager.createNativeQuery(DELETE_COURSE_FROM_STUDENT)
                    .setParameter("studentId",studentId)
                    .setParameter("courseName",courseName)
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void deleteAll() {
        try {
            log.debug("Delete all courses to students");
            entityManager.createNativeQuery(DELETE_ALL_STUDENT_TO_COURSE)
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }
}
