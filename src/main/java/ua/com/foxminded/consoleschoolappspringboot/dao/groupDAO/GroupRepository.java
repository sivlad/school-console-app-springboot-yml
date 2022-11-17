package ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;
import ua.com.foxminded.consoleschoolappspringboot.model.GroupsAndCounts;

import java.sql.SQLException;
import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Long> {

    String FIND_ALL_GROUPS_WITH_LESS_ORE_EQUAL_STUDENTS =
            """
                    SELECT groups.group_name, COUNT(*) AS count_students
                    FROM groups JOIN students ON groups.id = students.group_id
                    GROUP BY groups.group_name
                    HAVING COUNT(*) <= :numberOfStudents\s""";

    @Query(value = FIND_ALL_GROUPS_WITH_LESS_ORE_EQUAL_STUDENTS, nativeQuery = true)
    List<GroupsAndCounts> findAllCoursesFromStudent(@Param("numberOfStudents") int count) throws SQLException;

}
