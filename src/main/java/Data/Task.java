package Data;

import java.util.ArrayList;
import java.util.Date;

public class Task {
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private int duration;
    private Status status;
    private int nIssues = 0;

    private ArrayList<Issue> issues;

    public Task(Status s,String name) {
        this.status=s;
        this.name=name;
        this.nIssues=0;
        this.issues=new ArrayList<Issue>();
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

    public void nextStatus(){
        switch(this.status){
            case Todo -> {this.status=Status.Ongoing;}
            case Ongoing -> {this.status=Status.Finished;}
        }
    }

    public void previusStatus(){
        switch(this.status){
            case Finished -> {this.status=Status.Ongoing;}
            case Ongoing -> {this.status=Status.Todo;}
        }

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

    public int getnIssues() {
        return nIssues;
    }

    public void addIssues(Issue i) {
        this.nIssues++;
        this.issues.add(i);
    }

    public void deleteIssue(Issue i){
        if(nIssues<=0)return;
        nIssues--;
        this.issues.remove(i);
    }

    public ArrayList<Issue> getIssues() {
        return issues;
    }
}
