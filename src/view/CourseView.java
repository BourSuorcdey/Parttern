package view;

import exception.IsEmptyException;
import repository.CourseRepository;
import service.CourseServiceImp;

import java.util.Scanner;

public class CourseView {
    private final CourseRepository courseService = new CourseServiceImp();
    public void menu() throws IsEmptyException {
        while (true) {
            System.out.println("=".repeat(50));
            System.out.println("1. Add new Course");
            System.out.println("2. List Course");
            System.out.println("3. Find Course By ID");
            System.out.println("4. Find Course By Title");
            System.out.println("5. Remove Course By ID");
            System.out.println("0. Exit.");
            System.out.println("=".repeat(50));
            System.out.print("[+] Insert option: ");
            String option = new Scanner(System.in).nextLine();
            switch (option) {
                case "1" -> {
                    courseService.createCourse();
                }
                case "2" -> {
                    courseService.listAllCourse();
                }
                case "3" -> {
                    courseService.searchByID();
                }
                case "4" -> {
                    courseService.searchByName();
                }
                case "5" -> {
                    courseService.removeByID();
                }
                case "0" -> {
                    System.exit(0);
                }
                default -> System.out.println("Please, Input the right option [0->5]: ");
            }
        }
    }
}
