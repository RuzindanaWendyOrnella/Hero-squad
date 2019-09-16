import java.util.ArrayList;

public class Squad {
    private String squadName;
    private int maxSize;
    private String cause;
    private static ArrayList<Squad> instances = new ArrayList<>();
    public Squad(String squadName, int maxSize, String cause){
        this.squadName=squadName;
        this.maxSize =maxSize;
        this.cause=cause;
        instances.add(this);
    }
    public String getSquadName(){
        return squadName;
    }

    public String getCause(){
        return cause;
    }

    public int getMaxSize(){
        return maxSize;
    }
    public static ArrayList<Squad>getAll(){
        return instances;
    }

}
