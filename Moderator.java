import java.util.Scanner;
import player.*;
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

                    if(!isValidPlayerNum(total,input) || (i == 0 && input < 5) || (i == 1 && (input>=(nums[0]/2) || input<1))){
                        String prompt;
                        if(i == 0 && input < 5){
                            prompt = "Number of roles need to be 5 or higher.";
                        }else if(input < 0){
                            prompt = "Number needs to be 0 or higher";
                        }else if(i == 1 && (input>=(nums[0]/2) || input<1)){
                            prompt = "Invalid mafia number (has to be less than half the total or at least 1)";
                        }else{
                            prompt = "Number of roles selected are more than the number of players left.";
                        }
                        throw new Exception(prompt);
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
                    System.out.println("INVALID INPUT: " + e.getMessage());
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
        for(int i = 0; i<roleNums[3];i++){
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
