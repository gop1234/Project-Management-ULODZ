package Data;


import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private String description;
    private List<Task> tasks;
    private float price;


    public Project(String name) {
        this.name = name;
        tasks = new ArrayList<>();
        description ="";
        price =0;
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

    public void setTasks(List tasks) {
        this.tasks = tasks;
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
