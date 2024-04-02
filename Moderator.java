import java.util.Scanner;
import player.Player;
public class Moderator {
    MafiaGame mafiaGame;

    public Moderator(){
        int[] roleNums = getPlayers();
        Player[] players = generatePlayerRoles(roleNums);
        this.mafiaGame = new MafiaGame(players, roleNums[1], roleNums[2], roleNums[3]); 
    }
    private int[] getPlayers(){
        String[] inputPrompts = {"Enter the number of players: ","Enter the number of mafia: ",
        "Enter the number of sheriff: ","Enter the number of doctors: "};
        int[] nums = new int[4];
        int total = 1000;
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i<4; i++){
            while(true){
                try{
                    System.out.print(inputPrompts[i]);
                    int input = Integer.parseInt(scanner.nextLine());

                    if(!isValidPlayerNum(input, total) || (i == 1 && input == 0)){
                        throw new Exception("invalid number");
                    }
                    else{
                        if(i == 0){ //if selection is for number of players
                            total = input;
                        }
                        if(i != 0){ //if selection is for other roles, subtract from total
                            total-=input;
                        }
                        nums[i] = input;

                        break;
                    }
                } catch(Exception e){
                    System.out.println("INVALID INPUT: " + e);
                }
            }
        }
        return nums;
    }
    private Player[] generatePlayerRoles(int[] roleNums){
        Player[] result = new Player[roleNums[0]];
        int citizenNum = roleNums[0]-roleNums[1]-roleNums[2]-roleNums[3];
        int iterator = 0;
        //citizen
        for(int i = 0; i<citizenNum;i++){
            result[iterator] = new Player();
            iterator++;
        }
        //mafia
        for(int i = 0; i<roleNums[1];i++){
            result[iterator] = new Mafia();
            iterator++;
        }
        //sheriff
        for(int i = 0; i<roleNums[2];i++){
            result[iterator] = new Sheriff();
            iterator++;
        }
        //doctor
        for(int i = 0; i<roleNums[2];i++){
            result[iterator] = new Doctor();
            iterator++;
        }
        return result;
    }
    boolean isValidPlayerNum(int numOfPlayersLeft, int numSelected){
        if(numSelected > numOfPlayersLeft || numSelected<0){
            return false;
        }
        else{
            return true;
        }
    }
    public void startGame(){
        System.out.println("Starting as moderator....");
        mafiaGame.start();
    }
}
