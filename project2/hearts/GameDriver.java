package hearts;

import java.util.*;

class GameDriver {
  
  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
    
//  Interact mode. The game will run until the use choose not to continue.
    if (args == null || args.length == 0) {
      while (true) {
        System.out.println("How many players?");
        Scanner scanner = new Scanner(System.in);
        int num = Integer.valueOf(scanner.nextLine());
        startGame(num, 1);
        System.out.println("Do you want to play again(Y/N)?");
        char answer = scanner.nextLine().charAt(0);
        boolean playAgain = answer == 'y' || answer == 'Y';
        if (!playAgain) {
          scanner.close();
          System.exit(0);
        }
      }
    }
//  Silent mode. Take two arguments: numberOfPlayers [numberofMatches=1]
    else if (args.length < 3) {
      int playerNumber = Integer.valueOf(args[0]);
      int matchNumber = 1;
      if (args.length == 2) matchNumber = Integer.valueOf(args[1]);      
      startGame(playerNumber, matchNumber);
    } else {
      System.out.println("usage: java GameDriver numberOfPlayers [numberofMatches]");
    }
  }

  private static void startGame(int playerNumber, int matchNumber){
    while(matchNumber-->0){
      Game game = new Game(playerNumber);
      game.playAGame();
      System.out.println("*****Game Over*****");
    }
  }
}
