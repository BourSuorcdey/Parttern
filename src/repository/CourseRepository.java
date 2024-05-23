package repository;

public interface CourseRepository {
    void createCourse();
    void listAllCourse();
    void searchByID();
    void searchByName();
    void removeByID();
}
