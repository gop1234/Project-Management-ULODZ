package Data;


import java.util.ArrayList;
import java.util.List;

public class Project {
    private String Name;
    private List tasks;
    private float price;


    public Project(String name) {
        Name = name;
        tasks = new ArrayList<>();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List getTasks() {
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
}
