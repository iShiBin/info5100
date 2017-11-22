package hearts.test;

import static org.junit.Assert.*;

import org.junit.Test;

import hearts.Card;
import hearts.Hand;
import hearts.Suit;

public class HandTest {

  @Test
  public void test() {
    Hand h=new Hand(1, 3);
    h.addCard(new Card(2,Suit.Heart));
    h.addCard(new Card(4,Suit.Club));
    h.addCard(new Card(3,Suit.Club));
    
    h.sort();
    
    System.out.println(h);
  }

}
