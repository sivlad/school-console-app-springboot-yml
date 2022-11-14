package ua.com.foxminded.consoleschoolappspringboot.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "studentsandcourses")
public class StudentsToCourse{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private long studentId;

    @Column(name = "course_id")
    private long courseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentsToCourse that = (StudentsToCourse) o;
        return studentId == that.studentId
                && courseId == that.courseId
                && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, courseId);
    }

    @Override
    public String toString() {
        return "StudentsToCourse{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
