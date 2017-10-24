package hearts;

public class Card implements Comparable<Card>{
  private int num;
  private Suit suit;
  public static final Card QUEEN_OF_SPADES=new Card(12,Suit.Spade);
  public static final Card TWO_OF_CLUB=new Card(2,Suit.Club);
  
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
  
  public boolean hasSameSuit(Card c){
    return suit.equals(c.suit);
  }
  
  public boolean hasSameSuit(Suit s){
    return this.suit.equals(s);
  }

  @Override
  public int compareTo(Card c) {
    return num-c.num;
  }
  
  //todo which equals() will work in runtime?
  public boolean equals(Card c){
    return this.num==c.num && this.hasSameSuit(c);
  }
  
  @Override
  public boolean equals(Object o){
    if(this==o) return true;
    if(o==null || !o.getClass().equals("Card")) return false;
    
    Suit s=(Suit)o;
    return this.toString().equals(s.toString());
  }
  
  @Override
  public int hashCode(){
    return this.toString().hashCode();
  }
}