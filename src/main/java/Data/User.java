package Data;

import java.lang.invoke.WrongMethodTypeException;

public class User {
    String name;
    int role;

    public User(String name, int role){
        this.name=name;
        this.role=role;
    }

    public String getName(){return name;}

    public int getRole(){return role;}

    public String getRoleName(){
        switch(role){
            case 0 -> {return "Project Manager";}
            case 1 -> {return "Team leader";}
            case 2 -> {return "Normal User";}
        }
        return "Anonymus";
    }
}
