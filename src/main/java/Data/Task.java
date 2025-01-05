package Data;

import java.util.Date;

enum Status{
    Todo, Ongoing, Finished
}
public class Task {
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private int duration;
    private Status status;
}
