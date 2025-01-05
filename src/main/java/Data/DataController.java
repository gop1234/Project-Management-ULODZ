package Data;

import java.util.ArrayList;


/**
 * Singleton
 * Use to store all data project user everything*/
public class DataController {
    private static DataController instance;

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


    public ArrayList<Project> getProjetcs() {
        return projetcs;
    }

}
