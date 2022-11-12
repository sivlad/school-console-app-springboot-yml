package ua.com.foxminded.consoleschoolappspringboot.model;

import lombok.Data;

import javax.persistence.*;

@Data
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
}
