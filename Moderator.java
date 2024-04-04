import java.util.Scanner;
import player.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class Moderator {
    MafiaGame mafiaGame;

    public Moderator(){
        System.out.println("Requesting player roles...");
        int[] roleNums = getPlayers();
        System.out.println("Generating player roles...");
        Player[] players = generatePlayerRoles(roleNums);
        this.mafiaGame = new MafiaGame(players, roleNums[1], roleNums[2], roleNums[3]);
        System.out.println("Requesting player names...");
        String[] names = getNames(roleNums[0]);
        System.out.println("Assigning player names...");
        assignNames(names);
        
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
        System.out.printf("The players are: Citizen(%d), Mafia(%d), Sheriff(%d), Doctor(%d).\n", nums[0]-nums[1]-nums[2]-nums[3],nums[1],nums[2],nums[3]);
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
    private String[] getNames(int numberOfPlayers){
        String[] result = new String[numberOfPlayers];
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i<numberOfPlayers; i++){
            System.out.printf("Enter an identification for the player %d: ", i+1);
            result[i] = scanner.nextLine();
        }

        for(int i = 0; i<numberOfPlayers; i++){
            int index = rand.nextInt(i,numberOfPlayers);
            
            String temp = result[index];
            result[index] = result[0];
            result[0] = temp;
        }
        System.out.println("TEST, SHUFFLED NAMES: " + Arrays.toString(result));
        return result;
    }
    private void assignNames(String[] id){
        int iterator = 0;
        for(Player player : mafiaGame.players){
            player.setID(id[iterator]);
        }
    }
    boolean isValidPlayerNum(int numOfPlayersLeft, int numSelected){
        if(numSelected > numOfPlayersLeft || numSelected<0){
            return false;
        }
        else{
            return true;
        }
    }
    public void start(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting as moderator....");
        if(mafiaGame.timeOfDay == "day"){
            
        }else if (mafiaGame.timeOfDay == "night"){
            for(String sequence: mafiaGame.actionSequence){
                if(sequence == "S"){
                    for()
                }
            }
        }
        startRound();
    }
    public void startRound(){
        mafiaGame.start();
    }
}
