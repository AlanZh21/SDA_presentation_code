package player;
public class Player {
    boolean isAlive;
    boolean isProtected;
    String id;

    public Player(){
        this.isAlive = true;
        this.isProtected = false;
        this.id = "";
    }

    public void setID(String id){
        this.id = id;
    }
}
