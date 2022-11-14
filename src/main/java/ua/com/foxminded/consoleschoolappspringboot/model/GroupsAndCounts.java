package ua.com.foxminded.consoleschoolappspringboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class GroupsAndCounts {

    @Id
    private String groupName;

    @Column
    private long countStudents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupsAndCounts that = (GroupsAndCounts) o;
        return countStudents == that.countStudents
                && Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, countStudents);
    }

    @Override
    public String toString() {
        return "GroupsAndCounts{" +
                "groupName='" + groupName + '\'' +
                ", countStudents=" + countStudents +
                '}';
    }
}
