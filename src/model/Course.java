package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Integer id;
    private String title;
    private String[] instructorName;
    private String[] requirement;
    private Date startDate;
}
