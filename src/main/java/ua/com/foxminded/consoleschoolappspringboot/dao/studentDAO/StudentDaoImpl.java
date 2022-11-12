package ua.com.foxminded.consoleschoolappspringboot.dao.studentDAO;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.consoleschoolappspringboot.AppStarter;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;
import ua.com.foxminded.consoleschoolappspringboot.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
public class StudentDaoImpl implements StudentDao{

    private static final String SAVE_STUDENT = "INSERT INTO students(group_id,first_name,last_name) " +
            " VALUES((SELECT groups.id FROM groups WHERE group_name = :groupName),:firstName,:lastName)";
    private static final String SAVE_STUDENT_BRIEF = "INSERT INTO students(group_id,first_name,last_name) VALUES(:groupId,:firstName,:lastName)";
    private static final String UPDATE_STUDENT = "UPDATE students SET first_name= :firstName,last_name= :lastName WHERE id = :groupId";
    private static final String FIND_ALL_STUDENTS = "SELECT * FROM students";
    private static final String FIND_ALL_STUDENTS_FROM_COURSE = "SELECT students.id, students.group_id, students.first_name, " +
            " students.last_name FROM students JOIN studentsandcourses ON students.id = studentsandcourses.student_id " +
            " JOIN courses ON courses.id = studentsandcourses.course_id WHERE courses.course_name = :courseName ";
    private static final String DELETE_ALL_STUDENTS = "DELETE FROM students";
    private static final String DELETE_STUDENT = "DELETE FROM students WHERE students.id = :studentId";

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(String groupName, String firstName, String lastName){
        try {
            log.debug("Save student");
            entityManager.createNativeQuery(SAVE_STUDENT, Student.class)
                    .setParameter("groupName",groupName)
                    .setParameter("firstName",firstName)
                    .setParameter("lastName",lastName)
                    .executeUpdate();
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
        }
   }

    @Transactional
    @Override
    public void save(Student student){
        try {
            log.debug("Save student");
            entityManager.createNativeQuery(SAVE_STUDENT_BRIEF, Student.class)
                    .setParameter("groupId",student.getGroupId())
                    .setParameter("firstName",student.getFirstName())
                    .setParameter("lastName",student.getLastName())
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public int[] saveStudentsList(List<Student> students) {

        log.debug("Save list of students");
        for (Student currentStudent : students)
        {
            entityManager.createNativeQuery(SAVE_STUDENT_BRIEF, Course.class)
                    .setParameter("groupId",currentStudent.getGroupId())
                    .setParameter("firstName",currentStudent.getFirstName())
                    .setParameter("lastName",currentStudent.getLastName())
                    .executeUpdate();
        }
        return new int[] {};
    }

    @Transactional
    @Override
    public void update(Student student) {
        try {
            log.debug("Update student");
            entityManager.createNativeQuery(UPDATE_STUDENT, Course.class)
                    .setParameter("groupId",student.getGroupId())
                    .setParameter("firstName",student.getFirstName())
                    .setParameter("lastName",student.getLastName())
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void delete(long studentId) {
        try {
            log.debug("Delete student " + studentId);
            entityManager.createNativeQuery(DELETE_STUDENT)
                    .setParameter("studentId",studentId)
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public List<Student> findAll() {
        try {
            log.info("find all students");
            return entityManager.createNativeQuery(FIND_ALL_STUDENTS,Student.class).getResultList();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Transactional
    @Override
    public List<Student> findAllFromCourse(String courseName) {
        try {
            log.info("find all students from course");
            return entityManager.createNativeQuery(FIND_ALL_STUDENTS_FROM_COURSE,Student.class)
                    .setParameter("courseName", courseName)
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
            log.debug("Delete all students");
            entityManager.createNativeQuery(DELETE_ALL_STUDENTS)
                    .executeUpdate();
        } catch (DataAccessException e) {
            log.warn("Bad sql queue" + e.getMessage());
        }
    }
}
