package Data;

public class Issue {
    private String name;
    private String description;

    public Issue(String name){
        this.name=name;
        this.description="";
    }

    @Override
    public String toString(){
        return this.name;
    }
}
