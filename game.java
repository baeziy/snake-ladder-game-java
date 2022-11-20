import java.util.Scanner;
import java.util.Random;

public class game{

    public static void main(String []args){
        // Scanner object to take names from users 
        Scanner input = new Scanner(System.in);

        // taking input
        System.out.print("Enter Player 1 name: ");
        Player p1 = new Player(input.nextLine());

        System.out.print("Enter Player 2 name: ");
        Player p2 = new Player(input.nextLine());

        // clearing screen
        clearScreen();

        // Initialising gameplay object
        Gameplay play = new Gameplay(p1, p2);


        // closing scanner object
        input.close();

    }

    // function to clear screen
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    

}

class Gameplay{
    Scanner scanner = new Scanner(System.in);  
    
    // constructor calling play()
    public Gameplay(Player p1, Player p2){
        play(p1, p2);
    }

    // toss function which returns if player 1{always} has won or not
    public boolean toss(){
        if (Math.random() < 0.5)return true;
        else return false;
    }

    // play function which calls toss() and then calls gameInitiated()
    public void play(Player p1, Player p2){

        
        // asking user to press ENTER
        System.out.print("Press ENTER for the toss!");
        scanner.nextLine();
        game.clearScreen();

        // toss() being called
        boolean if_toss_winner_is_p1 = toss();

        // letting users if player 1 has won or player 2 has won the toss
        if (if_toss_winner_is_p1)System.out.println(p1.name + " has WON the toss!");
        else System.out.println(p2.name + " has WON the toss!");

        // asking user to press ENTER
        System.out.print("Press ENTER to Play Game");
        scanner.nextLine();
        game.clearScreen();

        // gameInitiated() being called
        gameInitiated(if_toss_winner_is_p1, p1, p2);
        

    }

    // gameInitiated function
    public void gameInitiated(boolean if_toss_winner_is_p1, Player p1, Player p2){
        // objects declared for toss winner and loser {tells who has the first turn}
        Player toss_winner = new Player();
        Player toss_loser = new Player();

        // setting toss winner and toss loser
        if (if_toss_winner_is_p1){
            toss_winner.setter(p1.name);
            toss_loser.setter(p2.name);
        }
        else{
            toss_winner.setter(p2.name);
            toss_loser.setter(p1.name);
        }

        // creating map for the game
        int[][] map = new int[3][100];
        create_map(map);



        // for(int i=0; i<3; i++){
        //     for(int j=0; j<100; j++){
        //          System.out.print(map[i][j] + " ");
        //     }
        //     System.out.println();

        // }
        int dice;
        while(true){
        game.clearScreen();

        System.out.print(toss_winner.name + " press ENTER to roll the dice!");
        scanner.nextLine();
        
        dice = roll_dice();

        // checking if toss winner has won the game.
        if(isWinner(toss_winner, dice)){
            System.out.println("\nPlayer: " + toss_winner.name + "\nPosition: " + toss_winner.position);
            System.out.println(toss_winner.name + "WINS!!!!!");
            System.exit(0);
        }






        }



    }

    public boolean isWinner(Player p, int dice){
        if(p.position + dice == 100){
            p.position += dice;
            return true;
        } 

        return false;

    }

    public void create_map(int map[][]){

        for(int i=0; i<3; i++){
            if(i == 0){
                for(int j=0; j<100; j++){
                    map[i][j] = j+1; 
                }
            }
            else if(i == 1){
                for(int j=0; j<100; j++){
                    map[i][j] = 0; 
                }
            }
            else{
                for(int j=0; j<100; j++){
                    map[i][j] = 0; 
                }

                // snake head
                map[1][98] = -1;
                map[1][69] = -1;
                map[1][51] = -1;
                map[1][24] = -1;
                map[1][94] = -1;

                // snake tail
                map[2][98] = 53;
                map[2][69] = 54;
                map[2][51] = 41;
                map[2][24] = 1;
                map[2][94] = 71;


                // ladder tail
                map[1][5] = -2;
                map[1][10] = -2;
                map[1][59] = -2;
                map[1][45] = -2;
                map[1][16] = -2;

                // ladder head
                map[2][5] = 24;
                map[2][10] = 39;
                map[2][59] = 84;
                map[2][45] = 89;
                map[2][16] = 68;
                
            }
        }
    }

    public int roll_dice(){

        Random rand = new Random();
        int randomNum = rand.nextInt((6 - 1) + 1) + 1;
        return randomNum;
    }
}

class Player{
    int position;
    String name;

    public Player(){position = 1;}
    public Player(String Name){
        name = Name;
        position = 1;
    }
    public void setter(String Name){
        name = Name;
    }
}