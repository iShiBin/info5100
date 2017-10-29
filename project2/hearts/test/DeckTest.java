package hearts.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import hearts.Deck;

public class DeckTest {

  @Test
  public void shuffleTest() {
    Deck deck=new Deck();
//    Assert.assertTrue("52!", deck.getCards().size()==52);
    deck.shuffle();
//    System.out.println(deck.getCards());
  }

}
