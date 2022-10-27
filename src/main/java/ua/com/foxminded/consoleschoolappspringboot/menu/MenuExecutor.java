package ua.com.foxminded.consoleschoolappspringboot.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.consoleschoolappspringboot.dao.groupDAO.GroupDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentDAO.StudentDao;
import ua.com.foxminded.consoleschoolappspringboot.dao.studentandcourseDAO.StudentsToCoursesDaoImpl;
import ua.com.foxminded.consoleschoolappspringboot.model.Group;
import ua.com.foxminded.consoleschoolappspringboot.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static ua.com.foxminded.consoleschoolappspringboot.menu.MenuPublisher.showMenu;
import static ua.com.foxminded.consoleschoolappspringboot.menu.MenuPublisher.showStringList;

@Service
public class MenuExecutor {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private StudentsToCoursesDaoImpl studentsToCoursesDao;

    @Autowired
    private MenuPublisher publisher;

    public void startMenu() {
        showMenu();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Please, enter the number of students");
            int numberOfStudents = scanner.nextInt();
            System.out.println(menuItem1Execute(numberOfStudents));
        }
        if (choice == 2) {
            System.out.println(publisher.showCoursesList());
            System.out.println("Please, enter the courses name");
            String courseName = scanner.nextLine();
            courseName = scanner.nextLine();
            System.out.println(menuItem2Execute(courseName));
        }
        if (choice == 3) {
            menuItem3Execute();
        }
        if (choice == 4) {
            menuItem4Execute();
        }
        if (choice == 5) {
            menuItem5Execute();
        }
        if (choice == 6) {
            menuItem6Execute();
        }
    }

    public String menuItem1Execute(int numberOfStudents) {
        List<String> groups = groupDao.findAllGroupsWithLessOreEqualStudentsNumber(numberOfStudents);

        System.out.println("Groups with less ore equal students nembers are");
        return showStringList(groups);
    }

    public String menuItem2Execute(String courseName) {
        List<Student> students = studentDao.findAllFromCourse(courseName);

        List<String> studentsList = students.stream().collect(
                ArrayList::new,
                (list, item) -> list.add(item.getFirstName() + " " + item.getLastName()),
                (list1, list2) -> list1.addAll(list2));

        return showStringList(studentsList);
    }

    public void menuItem3Execute() {
        Scanner scanner = new Scanner(System.in);
        publisher.showGroupList();
        System.out.println("Please, enter the group to add student");
        String groupName = scanner.nextLine();
        System.out.println("Please, enter the first name of student");
        String firstName = scanner.nextLine();
        System.out.println("Please, enter the last name of student");
        String lastName = scanner.nextLine();

        List<Group> groups = groupDao.findAll();
        try {
            Group groupToAdd = groups.stream().filter(e -> e.getGroupName().equals(groupName)).collect(Collectors.toList()).get(0);
            Student addStudent = new Student();
            addStudent.setFirstName(firstName);
            addStudent.setLastName(lastName);
            addStudent.setGroupId(groupToAdd.getId());
            studentDao.save(addStudent);

        } catch (IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
        }
    }

    public void menuItem4Execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the student id for delete");
        long studentId = scanner.nextLong();
        studentDao.delete(studentId);
    }

    public void menuItem5Execute() {
        Scanner scanner = new Scanner(System.in);

        publisher.showCoursesList();
        System.out.println("Please, enter the courses name, that assign to student");
        String courseName = scanner.nextLine();

        System.out.println("Please, enter the student id to assign");
        long studentId = scanner.nextLong();

        studentsToCoursesDao.assignStudentToCourse(studentId, courseName);
    }

    public void menuItem6Execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter the student id to assign");
        long studentId = scanner.nextLong();
        scanner.nextLine();

        publisher.showCoursesListStudent(studentId);
        System.out.println("Please, enter the course name to remove from student");
        String courseName = scanner.nextLine();

        studentsToCoursesDao.deleteCourseFromStudent(studentId, courseName);
    }
}

