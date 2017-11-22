package hearts.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import hearts.Game;

public class GameTest {
  @Test
  public void GameIntBoolean() {
    Random r=new Random();
    for(int i=0;i<1;i++){
      int n=5;//r.nextInt(51)+1;
      Game game=new Game(n, true);
      game.playAGame();
      System.out.println("*****GAME OVER*****\n");
    }
  }
}