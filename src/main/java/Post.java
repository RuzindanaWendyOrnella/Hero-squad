import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {

    private String name;
    private int age;
    private String power;
    private String weakness;
    private int id;
    private static ArrayList<Post> instances = new ArrayList<>();
    private boolean published;
    private LocalDateTime createdAt;
    public Post(String name, int age, String power, String weakness){

        this.name=name;
        this.age =age;
        this.power=power;
        this.weakness=weakness;
        this.published = false;
        this.createdAt=LocalDateTime.now();
        instances.add(this);
        this.id=instances.size();
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }
    public static Post findById(int id) {
        return instances.get(id-1);
    }
    public String getPower(){
        return power;
    };
    public String getWeakness(){
        return weakness;
    }
    public static ArrayList<Post> getAll(){
            return instances;
        }

        public static void clearAllPosts(){
            instances.clear();
        }

        public boolean getPublished(){ //new too
            return this.published;
        }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public void update(String name, int age,String power,String weakness){
        this.name =name;
        this.age=age;
        this.power= power;
        this.weakness=weakness;
    }

    public void deletePost() {
        instances.remove(id-1); //same reason
    }
}

