package ua.com.foxminded.consoleschoolappspringboot.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.consoleschoolappspringboot.model.Course;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;
import ua.com.foxminded.consoleschoolappspringboot.service.schoolinitservice.SchoolInitService;

import java.util.List;

@Service
public class MenuPublisher {

    private static final String  ACTION_STRING = "To do action press the number";
    private static final String  FIND_ALL_GROUPS = "Find all groups with less or equal students’ number";
    private static final String  FIND_ALL_STUDENTS = "Find all students related to the course with the given name";
    private static final String  ADD_STUDENT = "Add a new student";
    private static final String  DELETE_STUDENT = "Delete a student by the STUDENT_ID";
    private static final String  ADD_STUDENT_TO_COURSE = "Add a student to the course (from a list)";
    private static final String  REMOVE_STUDENT_FROM_COURSE = "Remove the student from one of their courses.";
    private static final String  INVATION_STRING = "Please enter the number";
    private static final String  SEPARATOR = "-";
    private static final String  DOT_SPACE = ".  ";
    private static final String  END_LINE = "\n";

    private static final String  LIST_OF_GROUPS = "List of groups";
    private static final String  LIST_OF_COURSES = "List of courses";

    @Autowired
    private SchoolInitService schoolInitService;

    public static String showMenu() {
        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();
        resultString.append(ACTION_STRING)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(FIND_ALL_GROUPS)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(FIND_ALL_STUDENTS)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(ADD_STUDENT)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(DELETE_STUDENT)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(ADD_STUDENT_TO_COURSE)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(REMOVE_STUDENT_FROM_COURSE)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE)
                .append(INVATION_STRING);

        return resultString.toString();
    }

    public String showGroupList() {
        List<Group> groups = schoolInitService.finaAllGroups();
        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();
        resultString.append(LIST_OF_GROUPS)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE);

        for (var currentGroup : groups) {
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append(currentGroup.getGroupName())
                    .append(END_LINE);
        }
        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        return resultString.toString();
    }

    public String showCoursesList() {
        List<Course> courses = schoolInitService.findAllCourses();

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();

        resultString.append(LIST_OF_COURSES)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE);

        for (var currentCourse : courses) {
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append(currentCourse.getCourseName())
                    .append(DOT_SPACE)
                    .append(currentCourse.getCourseDescription())
                    .append(END_LINE);
        }
        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        return resultString.toString();
    }

    public String showCoursesListStudent(long studentId) {
        List<Course> courses = schoolInitService.findAllCoursesFromStudent(studentId);

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();

        resultString.append(LIST_OF_COURSES)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE);

        for (var currentCourse : courses) {
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append(currentCourse.getCourseName())
                    .append(DOT_SPACE)
                    .append(currentCourse.getCourseDescription())
                    .append(END_LINE);
        }
        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        return resultString.toString();
    }

    public static String showStringList(List<String> sourceStringList) {
        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();

        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        for (String currentString : sourceStringList) {
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append(currentString)
                    .append(END_LINE);
        }

        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        return resultString.toString();
    }
}
