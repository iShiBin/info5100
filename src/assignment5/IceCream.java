package assignment5;

class IceCream extends DessertItem {
  private int cost;
  
  @Override
  public int getCost() {
    return cost;
  }
  
  IceCream(){}
  IceCream(String name, int price){
    super(name);
    this.cost=price;
  }
}
