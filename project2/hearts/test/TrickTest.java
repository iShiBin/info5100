package hearts.test;

import static org.junit.Assert.*;

import org.junit.Test;

import hearts.Card;
import hearts.Suit;
import hearts.Trick;

public class TrickTest {

  @Test
  public void test() {
    Trick trick=new Trick(4);
    
    trick.addCard(new Card(5,Suit.Club));
    trick.addCard(new Card(12,Suit.Club));
    trick.addCard(new Card(13,Suit.Heart));
    trick.addCard(new Card(4,Suit.Spade));
    
    int i=0;;
    for(Card c:trick.getCards()){
      trick.update(i++, c);
    }

    System.out.println(trick.toString());
  }

}
