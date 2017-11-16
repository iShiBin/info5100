package assignment5;

public class Candy extends DessertItem {
  private double weight;
  private int pricePerPound;

  /**
   * Round cost to nearest integer by casting (weight*pricePerPound+0.5)
   * @see assignment5.DessertItem#getCost()
   */
  public int getCost(){
    return (int) (weight*pricePerPound+0.5);
  }
  
  Candy(){} // you can remove these constructors as we don't want to create items without its attributes
  Candy(String name, double weight, int price){
    super(name);
    this.pricePerPound=price;
    this.weight=weight;
  }
  
  /**
   * Add Candy's weight and price information before its parent's toString()
   * @see assignment5.DessertItem#toString()
   */
  @Override
  public String toString(){
    StringBuilder builder=new StringBuilder();
    builder.append(weight+" lbs. @ "+pricePerPound+ " /lb.\n");
    builder.append(super.toString());
    
    return builder.toString();
  }
}
