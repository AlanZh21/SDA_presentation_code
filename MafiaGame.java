import player.*;
public class MafiaGame extends GameTimer{
    protected GameTimer gameTimer;
    protected Player[] players;
    
    protected int mafiaIsAlive;
    protected int sheriffIsAlive;
    protected int doctorIsAlive;

    public MafiaGame(Player[] players, int mafia, int sheriff, int doctor){
        super(mafia,sheriff,doctor);
        this.players = players;

        this.mafiaIsAlive = mafia;
        this.sheriffIsAlive = sheriff;
        this.doctorIsAlive = doctor; 
        
    }

    public void start(){
        System.out.println("Starting game....");
        gameTimer.start();
    }

}