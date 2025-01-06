package Data;


import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private String description;
    private ArrayList<Task> tasks;
    private ArrayList<String> categories;
    private float price;


    public Project(String name) {
        this.name = name;
        tasks = new ArrayList<>();
        description ="";
        price =0;
        categories=new ArrayList<String>();
    }

    public void updateTasks(ArrayList<Task> list) {
        boolean temp = false;
        for(Task l:list){
            temp=false;
            for(Task t:this.tasks){
                if(t.getName().equals(l.getName())){
                    t.update(l);
                    temp=true;
                }
            }
            if(!temp) tasks.add(l);
        }

        for(Task t:this.tasks){
            temp=false;
            for(Task l:list){
                if(t.getName().equals(l.getName())){
                    temp=true;
                }
            }
            if(!temp) tasks.remove(t);
        }
    }

    public void updateCategory(ArrayList<String> stemp) {
        categories=stemp;
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

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }



}
