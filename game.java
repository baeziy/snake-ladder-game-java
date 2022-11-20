import java.util.Scanner;
import java.util.Random;


// starting class
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

// main game class
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
        game.clearScreen();


        int dice;

        boolean greater_than_hundred_checker = false;
        while(true){
        

            /////////* Toss winner's turn *///////////
            System.out.print("\n\n" + toss_winner.name + " press ENTER to roll the dice!");
            scanner.nextLine();
            game.clearScreen();

            dice = roll_dice();
            System.out.println("\nDice Value: " + dice);


            // checking if toss winner has won the game.
            if(isWinner(toss_winner, dice)){
                System.out.println("Player: " + toss_winner.name + "\nPrevious Position: " + toss_loser.prev_pos + "\nNew Position: " + toss_winner.position);
                System.out.println("********  " + toss_winner.name + " WINS!!!!!" + "  ******");
                System.exit(0);
            }
            else{
                checkSnakeOrLadder(map, dice, toss_winner);
            }


            /////////* Toss loser's turn *///////////
            System.out.print("\n\n" + toss_loser.name + " press ENTER to roll the dice!");
            scanner.nextLine();
            game.clearScreen();
            
            dice = roll_dice();
            System.out.println("\nDice Value: " + dice);


            // checking if toss winner has won the game.
            if(isWinner(toss_loser, dice)){
                System.out.println("Player: " + toss_loser.name+ "\nPrevious Position: " + toss_loser.prev_pos + "\nNew Position: " + toss_loser.position);
                System.out.println("********  " + toss_loser.name + " WINS!!!!!" + "  ******");
                System.exit(0);
            }
            // checking + updating positions
            else{
                checkSnakeOrLadder(map, dice, toss_loser);
            }

        }



    }


    // function checks and updates the player postion according to snake or ladder
    public void checkSnakeOrLadder(int map[][], int dice, Player p){

        // if dice + current position value > 100 then try again next time
        if (p.position + dice > 100){
            System.out.println("Sorry :/ so close to win, try again next time? {dice + position > 100}");
            System.out.println("Player: " + p.name+ "\nPrevious Position: " + p.prev_pos + "\nNew Position: " + p.position);
            return;
        }

        // if snakes bites you
        else if(map[1][p.position-1 + dice] == -1){
            p.prev_pos =p.position;
            p.position = map[2][p.position-1 + dice];

            System.out.println("Oh no!!!! a SNAKE bit you");
            System.out.println("Player: " + p.name+ "\nPrevious Position: " + p.prev_pos + "\nNew Position: " + p.position);
        }

        // if you touch a ladder's tail
        else if (map[1][p.position-1 + dice] == -2){
            p.prev_pos =p.position;
            p.position = map[2][p.position-1 + dice];

            System.out.println("Hurrayy!!! you climed a ladder");
            System.out.println("Player: " + p.name + "\nPrevious Position: " + p.prev_pos+ "\nNew Position: " + p.position);
        }

        // normal scenario
        else{
            p.prev_pos =p.position;
            p.position += dice;
            System.out.println("Player: " + p.name+ "\nPrevious Position: " + p.prev_pos + "\n New Position: " + p.position);
        }

    }

    // checks if player has won or not
    public boolean isWinner(Player p, int dice){
        if(p.position + dice == 100){
            p.prev_pos = p.position;
            p.position += dice;
            return true;
        } 

        return false;

    }

    // function creates map for the whole game {puts snakes and ladders at multiple locations}
    public void create_map(int map[][]){

        for(int i=0; i<3; i++){

            // position filling at level 0
            if(i == 0){
                for(int j=0; j<100; j++){
                    map[i][j] = j+1; 
                }
            }

            // snakes and ladders filling at level 1
            else if(i == 1){
                for(int j=0; j<100; j++){
                    map[i][j] = 0; 
                }
            }

            // new position according to snake/ladder at level 2
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
                map[2][98] = 54;
                map[2][69] = 55;
                map[2][51] = 42;
                map[2][24] = 2;
                map[2][94] = 72;


                // ladder tail
                map[1][5] = -2;
                map[1][10] = -2;
                map[1][59] = -2;
                map[1][45] = -2;
                map[1][16] = -2;

                // ladder head
                map[2][5] = 25;
                map[2][10] = 40;
                map[2][59] = 85;
                map[2][45] = 90;
                map[2][16] = 69;
                
            }
        }
    }


    // function rolls dice and returns value
    public int roll_dice(){

        Random rand = new Random();
        int randomNum = rand.nextInt((6 - 1) + 1) + 1;
        return randomNum;
    }
}


// player class
class Player{
    int prev_pos;
    int position;
    String name;

    public Player(){position = 1; prev_pos = 1;}
    public Player(String Name){
        name = Name;
        position = 1;
        prev_pos = 1;
    }
    public void setter(String Name){
        name = Name;
    }
}