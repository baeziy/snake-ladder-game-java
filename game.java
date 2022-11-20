import java.util.Scanner;

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
        boolean toss_winner = toss();

        // letting users if player 1 has won or player 2 has won the toss
        if (toss_winner)System.out.println(p1.name + " has WON the toss!");
        else System.out.println(p2.name + " has WON the toss!");

        // asking user to press ENTER
        System.out.print("Press ENTER to continue");
        scanner.nextLine();
        game.clearScreen();

        // gameInitiated() being called
        gameInitiated(toss_winner);
        

    }

    public void gameInitiated(boolean toss_winner){
        



    }
}

class Player{
    int score;
    String name;

    public Player(String Name){
        name = Name;
        score = 0;
    }
}