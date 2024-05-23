package service;

import exception.IsEmptyException;
import model.Course;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import repository.CourseRepository;

import java.util.*;
import java.util.stream.Collectors;

public class CourseServiceImp implements CourseRepository {
    List<Course> courses = new ArrayList<>();
    @Override
    public void createCourse() {
        try {
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
            if ((title.isEmpty() || instructorName.isEmpty() || requirement.isEmpty())) {
                throw new IsEmptyException("Your data is not enough, please fill again.");
            } else {
                // create course object
                Course course = new Course(id, title, instructorNames, requirements, startDate);
                // add course to list
                courses.add(course);
            }
        } catch (IsEmptyException e) {
            System.out.println(e);
        }

    }

    @Override
    public void listAllCourse() {
        renderCourseToTable(courses);
    }

    @Override
    public void searchByID() {
        try {
            System.out.print("[+] Enter Course ID to search: ");
            String id = new Scanner(System.in).nextLine();

            if (!id.isEmpty()) {
                List<Course> foundCourse = courses.stream()
                        .filter(n -> n.getId().equals(id))
                        .collect(Collectors.toList());
                if (!foundCourse.isEmpty()) {
                    System.out.println("============Course found============");
                    renderCourseToTable(foundCourse);
                } else {
                    System.out.println("Course with ID " + id + " not found.");
                }
                return;
            }
                throw new IsEmptyException("You are not insert yet..!");
        } catch (IsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void searchByName() {
        try {
            System.out.print("[+] Enter Course Name to search: ");
            String title = new Scanner(System.in).nextLine().toLowerCase().trim();

            if (!title.isEmpty()) {
                List<Course> foundCourses = courses.stream()
                        .filter(n -> n.getTitle().toLowerCase().contains(title))
                        .collect(Collectors.toList());
                if (!foundCourses.isEmpty()) {
                    System.out.println("==========Courses found==========");
                    renderCourseToTable(foundCourses);
                } else {
                    System.out.println("No courses found with name containing \"" + title + "\".");
                }
                return;
            }
            throw new IsEmptyException("You are not insert yet..!");
        } catch (IsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeByID() {
        try {
            System.out.print("[+] Enter Course ID to remove: ");
            String id = new Scanner(System.in).nextLine();
            if (!id.isEmpty()) {
                boolean removed = courses.removeIf(n -> n.getId().equals(id));
                if (removed) {
                    System.out.println("Course with ID " + id + " has been removed.");
                } else {
                    System.out.println("Course with ID " + id + " not found.");
                }
                return;
            }
                throw new IsEmptyException("You are not insert yet..!");

        } catch (IsEmptyException e) {
            System.out.println(e.getMessage());
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
