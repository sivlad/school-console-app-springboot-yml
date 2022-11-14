package ua.com.foxminded.consoleschoolappspringboot.dao.courseDAO;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
public class CourseDaoImpl implements CourseDao {

    private static final String SAVE_COURSE = "INSERT INTO courses(id,course_name,course_description) VALUES(:courseId, :courseName, :courseDescription)";
    private static final String SAVE_COURSE_BATCH = "INSERT INTO courses(course_name,course_description) VALUES(:courseName, :courseDescription)";
    private static final String FIND_ALL_COURSES = "SELECT * FROM courses";
    private static final String FIND_ALL_COURSES_FROM_STUDENT = """
            SELECT courses_
            FROM Course courses_ JOIN StudentsToCourse studentsandcourses_ ON courses_.id = studentsandcourses_.courseId
            JOIN Student students_ ON studentsandcourses_.studentId = students_.id
            WHERE students_.id = :studentId\s""";
    private static final String DELETE_ALL_COURSES = "DELETE FROM courses";
    private static final String UPDATE_COURSE = "UPDATE courses SET course_name = :courseName, course_description = :courseDescription " +
            " WHERE courses.id = :courseId ";
    private static final String DELETE_COURSE = "DELETE FROM courses WHERE courses.id = :courseId";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Course course) {
        try {
            log.debug("Save course");
            entityManager.createNativeQuery(SAVE_COURSE,Course.class)
                    .setParameter("courseId",course.getId())
                    .setParameter("courseName",course.getCourseName())
                    .setParameter("courseDescription",course.getCourseDescription())
                    .executeUpdate();
       } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public int[] saveCourseList(List<Course> courses) {
        log.debug("Save list of courses");
        for (Course currentCourse : courses)
        {
            entityManager.createNativeQuery(SAVE_COURSE_BATCH,Course.class)
                    .setParameter("courseName",currentCourse.getCourseName())
                    .setParameter("courseDescription",currentCourse.getCourseDescription())
                    .executeUpdate();

        }
        return new int[] {};
    }

    @Transactional
    @Override
    public void update(Course course) {
        try {
            log.debug("Update course");
            entityManager.createNativeQuery(UPDATE_COURSE,Course.class)
                    .setParameter("courseId",course.getId())
                    .setParameter("courseName",course.getCourseName())
                    .setParameter("courseDescription",course.getCourseDescription())
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void delete(Course course) {
        try {
            log.debug("Delete course");
            entityManager.createNativeQuery(DELETE_COURSE)
                    .setParameter("courseId",course.getId())
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<Course> findAll() {
        try {
            log.info("Find all courses");
            return entityManager.createNativeQuery(FIND_ALL_COURSES,Course.class).getResultList();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Transactional
    @Override
    public List<Course> findAllCoursesFromStudent(long studentId) {
        try {
            log.info("Fina all courses from student");
            return entityManager.createQuery(FIND_ALL_COURSES_FROM_STUDENT,Course.class)
                    .setParameter("studentId",studentId)
                    .getResultList();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Transactional
    @Override
    public void deleteAll() {
        try {
            log.debug("Delete all courses");
            entityManager.createNativeQuery(DELETE_ALL_COURSES)
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }
}
