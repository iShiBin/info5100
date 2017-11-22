package hearts.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;

import hearts.*;

public class CardTest {

  @Test
  public void displayTest() {
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
    
    Assert.assertTrue(card.hasSameSuit(card0));
    Assert.assertTrue(card.hasSameSuit(Suit.Club));
  }
  
  @Test
  public void equalsHashCodeTest(){
    Card card0=new Card(10, Suit.Club);    
    Card card1=new Card(10, Suit.Club);
    
    Assert.assertTrue("Two cards are not equal!", card0.equals(card1));
    
    List<Card> list=new ArrayList<>();
    list.add(card0);

    Assert.assertTrue(list.size()==1);

    list.remove(card1);
    System.out.println(list);
    Assert.assertTrue("remove() failed", list.size()==0);
  }

}
