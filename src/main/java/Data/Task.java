package Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Task {
    private String group;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private long duration;
    private float price;
    private String currency;
    private Status status;
    private int nIssues = 0;

    private ArrayList<Issue> issues;

    public Task(Status s,String name) {
        this.duration=0;
        this.status=s;
        this.name=name;
        this.nIssues=0;
        this.issues=new ArrayList<Issue>();
        this.price=0;
        this.currency = "EUR";
    }

    public void update(Task t){

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
