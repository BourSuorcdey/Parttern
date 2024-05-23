package service;

import model.Course;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import repository.CourseRepository;

import java.util.*;

public class CourseServiceImp implements CourseRepository {
    List<Course> courses = new ArrayList<>();
    @Override
    public void createCourse() {
        System.out.println("-".repeat(50));
        String id = String.valueOf(Integer.parseInt(String.valueOf(new Random().nextInt(1000))));
        System.out.print("[+] Insert title: ");
        String title = new Scanner(System.in).nextLine().trim();
        System.out.print("[+] Insert instructor name: ");
        String instructorName = new Scanner(System.in).nextLine().trim();
        String[] instructorNames = instructorName.split(",");
        System.out.print("[+] Insert requirement: ");
        String requirement = new Scanner(System.in).nextLine().trim();
        String[] requirements = requirement.split(",");
        Date startDate = new Date();

        // create course object
        Course course = new Course(id, title, instructorNames, requirements, startDate);

        // add course to list
        courses.add(course);
    }

    @Override
    public void listAllCourse() {
        renderCourseToTable(courses);
    }

    @Override
    public void searchByID() {
        System.out.print("[+] Enter Course ID to search: ");
        String id = new Scanner(System.in).nextLine();

        List<Course> foundCourse = new ArrayList<>();
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                foundCourse.add(course);
                System.out.println("============Course found============");
                renderCourseToTable(foundCourse);
                break;
            } else {
                System.out.println("Course with ID " + id + " not found.");
            }
        }
    }

    @Override
    public void searchByName() {
        System.out.print("[+] Enter Course Name to search: ");
        String title = new Scanner(System.in).nextLine().toLowerCase().trim();

        List<Course> foundCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getTitle().toLowerCase().contains(title)) {
                foundCourses.add(course);
            }
        }

        if (!foundCourses.isEmpty()) {
            System.out.println("==========Courses found==========");
            renderCourseToTable(foundCourses);
        } else {
            System.out.println("No courses found with name containing \"" + title + "\".");
        }
    }

    @Override
    public void removeByID() {
        System.out.print("[+] Enter Course ID to remove: ");
        String id = new Scanner(System.in).nextLine();

        //List<Course> courseToRemove = new ArrayList<>();
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                //courseToRemove.add(course);
                courses.remove(course);
                System.out.println("Course with ID " + id + " has been removed.");
                break;
            } else {
                System.out.println("Course with ID " + id + " not found.");
            }
        }
    }


    // Render table
    public void renderCourseToTable(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("=+= No courses available.");
            return;
        }

        Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
        for (int i = 0; i < 5; i++) {
            table.setColumnWidth(i, 20, 20);
        }

        // Header table
        table.addCell("Id");
        table.addCell("Course Name");
        table.addCell("Instructors");
        table.addCell("Requirements");
        table.addCell("Start Date");

        // Data rows
        for (Course course : courses) {
            table.addCell(String.valueOf(course.getId()));
            table.addCell(course.getTitle());
            table.addCell(String.join(", ", course.getInstructorName()));
            table.addCell(String.join(", ", course.getRequirement()));
            table.addCell(course.getStartDate().toString());
        }

        System.out.println(table.render());
    }
}
