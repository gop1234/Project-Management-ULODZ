package Data;

import java.util.ArrayList;


/**
 * Singleton
 * Use to store all data project user everything*/
public class DataController {
    private static DataController instance;
    private User logedUser;

    private ArrayList<Project> projetcs;

    private DataController(){
        projetcs= new ArrayList<Project>();
    }

    public static DataController getInstance() {
        if (instance == null) {
            instance = new DataController();
        }
        return instance;
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
}
