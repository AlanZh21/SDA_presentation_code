import java.util.Scanner;

public class GameTimer{
    int round;
    String timeOfDay;
    String[] actionSequence;

    public GameTimer(int mafia, int sheriff, int doctor){
        this.round = 0;
        this.timeOfDay = "day";
        setActionSequence(mafia, sheriff, doctor);
    }
    public void setActionSequence(int mafia, int sheriff, int doctor) {
        if(sheriff != 0 && doctor != 0){
            this.actionSequence = new String[]{"S","D","M"};
        }
        else if(sheriff != 0 && doctor == 0){
            this.actionSequence = new String[]{"S","M"};
        }
        else if(sheriff == 0 && doctor == 0){
            this.actionSequence = new String[]{"M"};
        }
    }
    protected void startTimer(){
        System.out.println("Starting Game Timer...");
        beginRound();
    }
    public void beginRound(){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Round: %s, %s\n", round, timeOfDay);
        System.out.println("Begin Discussion...");
        while(true){
            System.out.print("Moderater, enter any key to end discussion: ");
            if(!scanner.nextLine().isBlank()){
                break;
            }
        }
        timeOfDay = "night";
        round++;
        scanner.close();
    }
}