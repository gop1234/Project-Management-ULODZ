package Data;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Singleton
 * Use to store all data project user everything*/
public class DataController {
    private static DataController instance;
    private User logedUser;

    private ArrayList<Project> projetcs;

    private ArrayList<GeneralExpenses> generalExpenses;

    private DataController(){
        projetcs= new ArrayList<Project>();
        generalExpenses = new ArrayList<GeneralExpenses>();
        generateData();

    }

    public static DataController getInstance() {
        if (instance == null) {
            instance = new DataController();
        }
        return instance;
    }

    /**
     * Function to generate sample data*/
    public void generateData(){
        for(int i= 1; i <=12;i++){
            generalExpenses.add(new GeneralExpenses("Office rent", "Expense",450,"EUR",LocalDate.of(2025,i,27)));
            generalExpenses.add(new GeneralExpenses("Salary person 1", "Expense",2500,"EUR",LocalDate.of(2025,i,1)));
            generalExpenses.add(new GeneralExpenses("Royalties", "Income",1000,"EUR",LocalDate.of(2025,i,1)));
        }
        generalExpenses.add(new GeneralExpenses("Software License", "Applications",2500,"EUR",LocalDate.of(2025,12,31)));

        Project p1 = new Project("Shoping Aplication");
        p1.setDescription("""
                Shoping web Aplication for inditex 
                Create a new aplication for web shopping 
                """);
        projetcs.add(p1);


        Project p2 = new Project("Project Management Tool");
        p2.setDescription("""
                Create a new Management tool for Trello
                Improve Trello jira by creating a new version with new features.
                """);
        projetcs.add(p2);
    }

    public float changeCurrency(float amount,String old, String newC){

        if(old.equals("EUR")){
            if(newC.equals("PLN")){
                return (float) (amount*4.16);
            }
            if(newC.equals("USD")){
                return (float) (amount*1.03);
            }
        }
        if(old.equals("PLN")){
            if(newC.equals("EUR")){
                return (float) (amount/4.16);
            }
            if(newC.equals("USD")){
                return (float) ((amount/4.16)*1.03);
            }
        }
        if(old.equals("USD")){
            if(newC.equals("PLN")){
                return (float) ((amount/1.03)*4.16);
            }
            if(newC.equals("EUR")){
                return (float) (amount/1.03);
            }
        }
        return amount;
    }

    public void setLogedUser(User user){
        this.logedUser=user;
    }



    public ArrayList<Project> getProjetcs() {
        return projetcs;
    }

    public User getLogedUser() {
        return logedUser;
    }

    public ArrayList<GeneralExpenses> getGeneralExpenses() {
        return generalExpenses;
    }
}
