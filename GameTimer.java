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
            this.actionSequence = new String[]{"M", "S","D"};
        }
        else if(sheriff != 0 && doctor == 0){
            this.actionSequence = new String[]{"M","S"};
        }
        else if(sheriff == 0 && doctor == 0){
            this.actionSequence = new String[]{"M"};
        }
    }
    protected void start(){
        System.out.println("Starting Game Timer...");
        System.out.println(timeOfDay);
    }
}