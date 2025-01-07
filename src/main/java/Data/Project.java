package Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Project {
    private String name;
    private String description;
    private ArrayList<Task> tasks;
    private ArrayList<String> categories;
    private float price;

    // Existing fields
    private ArrayList<IncomeExpense> incomeExpenses; // Here stores IncomeExpense info

    // New field for payments
    private ArrayList<Payment> payments; // Here stores payments info




    public Project(String name) {
        this.name = name;
        this.description = "";
        this.price = 0;
        this.tasks = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.incomeExpenses = new ArrayList<>();
        this.payments = new ArrayList<>(); // Initialize the payments list
    }

    // Getters and setters
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Task> getTasks() {
        return tasks;
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

        Iterator<Task> taskIterator = tasks.iterator();

        // Iterate over the tasks list
        while (taskIterator.hasNext()) {
            Task t = taskIterator.next();
            temp = false;

            // Check if the task name exists in the other list
            for (Task l : list) {
                if (t.getName().equals(l.getName())) {
                    temp = true;
                    break; // Exit the inner loop once a match is found
                }
            }

            // If no match was found, remove the task from the tasks list
            if (!temp) {
                taskIterator.remove();
            }
        }
    }


    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }


    public void updateCategory(ArrayList<String> stemp) {
        this.categories = stemp;
    }

    // Methods for income and expenses
    public ArrayList<IncomeExpense> getIncomeExpenses() {
        return incomeExpenses;
    }

    public void addIncomeExpense(IncomeExpense incomeExpense) {
        this.incomeExpenses.add(incomeExpense);
    }

    public float calculateProfitLoss() {
        float income = 0;
        float expenses = 0;

        for (IncomeExpense ie : incomeExpenses) {
            if (ie.getType().equals("Income")) {
                income += ie.getAmount();
            } else if (ie.getType().equals("Expense")) {
                expenses += ie.getAmount();
            }
        }

        return income - expenses;
    }

    // Methods for payments
    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void addPayment(Payment payment) {
        this.payments.add(payment);
    }

}
