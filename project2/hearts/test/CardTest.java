package hearts.test;

import static org.junit.Assert.*;

import org.junit.Test;

import hearts.*;

public class CardTest {

  @Test
  public void test() {
    Card card=new Card(2, Suit.Club);
    card.display();
    
    Card card0=new Card(10, Suit.Club);
    card0.display();
    
    Card card1=new Card(11, Suit.Club);
    card1.display();
    
    Card card2=new Card(12, Suit.Diamond);
    card2.display();
    
    Card card3=new Card(13, Suit.Heart);
    card3.display();
    
    Card card4=new Card(14, Suit.Spade);
    card4.display();
    
    System.out.println(card.hasSameSuit(card0));
    
    System.out.println(card.hasSameSuit(Suit.Club));
  }

}
