package Data;

import java.time.LocalDate;
import java.util.Date;

public class Issue {
    private  String name;
    private String description;
    private LocalDate creationDate;

    public Issue(String name, String description, LocalDate creationDate){
        this.name=name;
        this.description=description;
        this.creationDate=creationDate;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
