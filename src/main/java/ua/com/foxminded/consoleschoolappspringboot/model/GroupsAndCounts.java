package ua.com.foxminded.consoleschoolappspringboot.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class GroupsAndCounts {

    @Id
    private String groupName;

    @Column
    private long countStudents;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getCountStudents() {
        return countStudents;
    }

    public void setCountStudents(long countStudents) {
        this.countStudents = countStudents;
    }

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
