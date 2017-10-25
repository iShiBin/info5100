package hearts;

import java.util.Comparator;

public class Card implements Comparable<Card>{
  private int num;
  private Suit suit;
  public static final Card QUEEN_OF_SPADES=new Card(12,Suit.Spade);
  public static final Card TWO_OF_CLUB=new Card(2,Suit.Club);
  public static final Comparator<Card> COMPARATOR_NUM=Comparator.comparing(Card::getNum);
  
  public Card(int num, Suit suit){
    this.num=num;
    this.suit=suit;
  }
  
  public int getNum(){return num;}
  public Suit getSuit() {return suit;}
  
  public void display(){
    System.out.println(this);
  }
  
  public String toString(){
    String cardString = new String();
    if(num>=2 && num<=10){
      cardString=String.valueOf(num);
    }else if(num>=11 && num<=14) {
      switch (num){
      case 11:cardString="J"; break;
      case 12:cardString="Q"; break;
      case 13:cardString="K"; break;
      case 14:cardString="A"; break;
      }
    }else{
      cardString="Invalid "+ suit.toString() + num;
    }
    return suit.toString()+cardString;
  }
  
  public boolean hasSameSuit(Card card){
    return suit.equals(card.suit);
  }
  
  public boolean hasSameSuit(Suit suit){
    return this.suit.equals(suit);
  }

  //todo: do i need to implement the compareto(Object o)
  @Override
  public int compareTo(Card card) {
    assert this.hasSameSuit(card);
    return this.num-card.num;
  }
  
  public boolean isBiggerThan(Card card){
    assert this.hasSameSuit(card);
    return this.compareTo(card)>0;
  }
  
  public boolean isSmallerThan(Card card){
    return !isBiggerThan(card);
  }
  
  //todo which equals() will work in runtime?
  public boolean equals(Card card){
    return this.num==card.num && this.hasSameSuit(card);
  }
  
  @Override
  public boolean equals(Object object){
    if(this==object) return true;
    if(object==null || !object.getClass().equals("Card")) return false;
    
    Suit s=(Suit)object;
    return this.toString().equals(s.toString());
  }
  
  @Override
  public int hashCode(){
    return this.toString().hashCode();
  }
}