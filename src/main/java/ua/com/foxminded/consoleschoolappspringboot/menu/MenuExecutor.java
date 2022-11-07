package ua.com.foxminded.consoleschoolappspringboot.menu;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.consoleschoolappspringboot.model.Student;
import ua.com.foxminded.consoleschoolappspringboot.service.schoolservice.SchoolService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ua.com.foxminded.consoleschoolappspringboot.menu.MenuPublisher.showMenu;
import static ua.com.foxminded.consoleschoolappspringboot.menu.MenuPublisher.showStringList;

@Log4j2
@Service
public class MenuExecutor {

    @Autowired
    private MenuPublisher publisher;

    @Autowired
    private SchoolService schoolService;

    public void startMenu() {
        System.out.println(showMenu());

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Please, enter the number of students");
            int numberOfStudents = scanner.nextInt();
            System.out.println(menuItem1Execute(numberOfStudents));
        }
        else if (choice == 2) {
            System.out.println(publisher.showCoursesList());
            System.out.println("Please, enter the courses name");
            String courseName = scanner.nextLine();
            courseName = scanner.nextLine();
            System.out.println(menuItem2Execute(courseName));
        }
        else if (choice == 3) {
            menuItem3Execute();
        }
        else if (choice == 4) {
            menuItem4Execute();
        }
        else if (choice == 5) {
            menuItem5Execute();
        }
        else if (choice == 6) {
            menuItem6Execute();
        }
        else {
            log.error("Invalid item of menu");
        }
    }

    public String menuItem1Execute(int numberOfStudents) {
        List<String> groups = schoolService.findAllGroupsWithLessOrEqualsStudentCount(numberOfStudents);

        System.out.println("Groups with less ore equal students nembers are");
        return showStringList(groups);
    }

    public String menuItem2Execute(String courseName) {
        List<Student> students = schoolService.findAllStudentsRelatedToTheCourseWithTheGivenName(courseName);

        List<String> studentsList = students.stream().collect(
                ArrayList::new,
                (list, item) -> list.add(item.getFirstName() + " " + item.getLastName()),
                (list1, list2) -> list1.addAll(list2));

        return showStringList(studentsList);
    }

    public void menuItem3Execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(publisher.showGroupList());
        System.out.println("Please, enter the group to add student");
        String groupName = scanner.nextLine();
        System.out.println("Please, enter the first name of student");
        String firstName = scanner.nextLine();
        System.out.println("Please, enter the last name of student");
        String lastName = scanner.nextLine();

        schoolService.addNewStudent(groupName,firstName,lastName);
    }

    public void menuItem4Execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the student id for delete");
        long studentId = scanner.nextLong();
        schoolService.deleteStudent(studentId);
    }

    public void menuItem5Execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(publisher.showCoursesList());
        System.out.println("Please, enter the courses name, that assign to student");
        String courseName = scanner.nextLine();

        System.out.println("Please, enter the student id to assign");
        long studentId = scanner.nextLong();

        schoolService.addStudentToTheCourse(studentId, courseName);
    }

    public void menuItem6Execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter the student id to assign");
        long studentId = scanner.nextLong();
        scanner.nextLine();

        System.out.println(publisher.showCoursesListStudent(studentId));
        System.out.println("Please, enter the course name to remove from student");
        String courseName = scanner.nextLine();

        schoolService.removeStudentFromCourse(studentId, courseName);
    }
}

