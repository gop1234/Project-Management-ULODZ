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
        generalExpenses.add(new GeneralExpenses("h","d",200,"PLN", LocalDate.now()));
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


        return 0;
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
