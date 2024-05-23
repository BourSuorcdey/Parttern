import exception.IsEmptyException;
import view.CourseView;

public class Main {
    public static void main(String[] args) {
        CourseView courseView = new CourseView();
        try {
            courseView.menu();
        } catch (IsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }
}