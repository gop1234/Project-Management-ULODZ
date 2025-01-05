package Data;

import java.util.Date;
import Data.Status;

public class Task {
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private int duration;
    private Status status;

    public Task(Status s,String name) {
        this.status=s;
        this.name=name;
    }
    @Override
    public String toString(){
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
