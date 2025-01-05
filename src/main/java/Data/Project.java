package Data;


import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private String description;
    private ArrayList<Task> tasks;
    private float price;


    public Project(String name) {
        this.name = name;
        tasks = new ArrayList<>();
        description ="";
        price =0;
        tasks.add(new Task(Status.Todo, "t1"));
        tasks.add(new Task(Status.Ongoing, "t2"));
        tasks.add(new Task(Status.Finished, "t3"));
        tasks.add(new Task(Status.Todo, "t4"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }



    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
