package ua.com.foxminded.consoleschoolappspringboot.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class GroupsAndCounts {

    @Id
    private String groupName;

    @Column
    private long countStudents;

}
