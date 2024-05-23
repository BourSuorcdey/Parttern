package repository;

import model.Course;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CourseRepository {
    public static List<Course> addCourse(){
        System.out.println("-".repeat(50));
        Integer id = new java.util.Random().nextInt(100000);
        System.out.print("[+] Insert title: ");
        String title = new Scanner(System.in).next();
        System.out.print("[+] Insert instructor name: ");
        String instructorName = new Scanner(System.in).next();
        String[] instructorNames = instructorName.split(",");
        System.out.print("[+] Insert requirement: ");
        String requirement = new Scanner(System.in).next();
        String[] requirements = requirement.split(",");
        Date startDate = new Date();

        // create course object
        Course course = new Course(id, title, instructorNames, requirements, startDate);

        // add course to list
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        return courses;
    }
}
