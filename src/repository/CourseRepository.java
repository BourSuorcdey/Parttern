package repository;

import exception.IsEmptyException;

public interface CourseRepository {
    void createCourse() throws IsEmptyException;
    void listAllCourse();
    void searchByID();
    void searchByName();
    void removeByID();
}
