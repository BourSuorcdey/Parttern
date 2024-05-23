package service;

import model.Course;

import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CourseServiceImp implements CourseService{
    List<Course> courses = new ArrayList<>();
    @Override
    public void createCourse() {
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
        courses.add(course);
    }

    @Override
    public void listAllCourse() {
        if (courses.isEmpty()) {
            System.out.println("=+= No courses available.");
        }
        List<String> headers = new ArrayList<>();
        headers.add("Course ID");
        headers.add("Title");
        headers.add("Instructors");
        headers.add("Requirements");
        headers.add("Start Date");

        List<List<String>> rows = new ArrayList<>();

        for (Course course : courses) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(course.getId()));
            row.add(course.getTitle());
            row.add(String.join(", ", course.getInstructorName()));
            row.add(String.join(", ", course.getRequirement()));
            row.add(course.getStartDate().toString());
            rows.add(row);
        }
        renderTable(headers, rows);
    }

    @Override
    public void searchByID() {
        System.out.print("[+] Enter Course ID to search: ");
        Integer id = new Scanner(System.in).nextInt();

        Course foundCourse = null;
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                foundCourse = course;
                break;
            }
        }

        if (foundCourse != null) {
            System.out.println("Course found:");
            List<String> headers = List.of("Course ID", "Title", "Instructors", "Requirements", "Start Date");
            List<List<String>> rows = List.of(
                    List.of(
                            String.valueOf(foundCourse.getId()),
                            foundCourse.getTitle(),
                            String.join(", ", foundCourse.getInstructorName()),
                            String.join(", ", foundCourse.getRequirement()),
                            foundCourse.getStartDate().toString()
                    )
            );
            renderTable(headers, rows);
        } else {
            System.out.println("Course with ID " + id + " not found.");
        }
    }

    @Override
    public void searchByName() {
        System.out.print("[+] Enter Course Name to search: ");
        String name = new Scanner(System.in).nextLine().toLowerCase();

        List<Course> foundCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getTitle().toLowerCase().contains(name)) {
                foundCourses.add(course);
            }
        }

        if (!foundCourses.isEmpty()) {
            System.out.println("Courses found:");
            List<String> headers = List.of("Course ID", "Title", "Instructors", "Requirements", "Start Date");
            List<List<String>> rows = new ArrayList<>();

            for (Course foundCourse : foundCourses) {
                List<String> row = new ArrayList<>();
                row.add(String.valueOf(foundCourse.getId()));
                row.add(foundCourse.getTitle());
                row.add(String.join(", ", foundCourse.getInstructorName()));
                row.add(String.join(", ", foundCourse.getRequirement()));
                row.add(foundCourse.getStartDate().toString());
                rows.add(row);
            }
            renderTable(headers, rows);
        } else {
            System.out.println("No courses found with name containing \"" + name + "\".");
        }
    }

    @Override
    public void removeByID() {
        System.out.print("[+] Enter Course ID to remove: ");
        Integer id = new Scanner(System.in).nextInt();

        Course courseToRemove = null;
        for (Course course : courses) {
            if (course.getId() == id) {
                courseToRemove = course;
                break;
            }
        }

        if (courseToRemove != null) {
            courses.remove(courseToRemove);
            System.out.println("Course with ID " + id + " has been removed.");
        } else {
            System.out.println("Course with ID " + id + " not found.");
        }
    }


    public static void renderTable(List<String> headers, List<List<String>> rows) {
        // Determine the maximum width for each column
        int[] columnWidths = new int[headers.size()];
        for (int i = 0; i < headers.size(); i++) {
            columnWidths[i] = headers.get(i).length();
        }

        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i).length() > columnWidths[i]) {
                    columnWidths[i] = row.get(i).length();
                }
            }
        }

        // Print header
        printRow(headers, columnWidths);
        printSeparator(columnWidths);

        // Print rows
        for (List<String> row : rows) {
            printRow(row, columnWidths);
        }
    }

    private static void printRow(List<String> row, int[] columnWidths) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row.size(); i++) {
            sb.append(String.format("| %-"+columnWidths[i]+"s ", row.get(i)));
        }
        sb.append("|");
        System.out.println(sb.toString());
    }

    private static void printSeparator(int[] columnWidths) {
        StringBuilder sb = new StringBuilder();
        for (int width : columnWidths) {
            sb.append("+");
            sb.append(new String(new char[width + 2]).replace("\0", "-"));
        }
        sb.append("+");
        System.out.println(sb.toString());
    }
}
