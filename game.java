import java.util.Scanner;

public class game{

    public static void main(String []args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Player 1 name: ");
        Player p1 = new Player(input.nextLine());

        System.out.print("Enter Player 2 name: ");
        Player p2 = new Player(input.nextLine());

        clearScreen();

        Gameplay play = new Gameplay(p1, p2);

        input.close();

    }
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  

        System.out.flush();  

    }

    

}

class Gameplay{
    Scanner scanner = new Scanner(System.in);  
    public void clearScreen(){
        System.out.print("\033[H\033[2J");  

        System.out.flush();  

    }
    
    public Gameplay(Player p1, Player p2){
        play(p1, p2);
    }
    public boolean toss(){
        if (Math.random() < 0.5){
            return true;
    }else{
            return false;
    }
    }
    public void play(Player p1, Player p2){

      
      System.out.print("Press ENTER for the toss!");
      scanner.nextLine();
      clearScreen();

      boolean toss_winner = toss();
      if (toss_winner)System.out.println(p1.name + " has WON the toss!");
      else System.out.println(p2.name + " has WON the toss!");

      System.out.print("Press ENTER to continue");
      scanner.nextLine();
      clearScreen();
      
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