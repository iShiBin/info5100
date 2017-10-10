package assignment5;

public class Candy extends DessertItem {
  private double weight;
  private int price;

  public int getCost(){
    return (int) (weight*price+0.5);
  }
  
  Candy(){}
  Candy(String name, double weight, int price){
    super(name);
    this.price=price;
    this.weight=weight;
  }
  
  public String toString(){
    StringBuilder builder=new StringBuilder();
    builder.append(weight+" lbs. @ "+price+ " /lb.\n");
    builder.append(super.toString());
    
    return builder.toString();
  }
}
