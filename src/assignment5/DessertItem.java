package assignment5;

public abstract class DessertItem {
  protected String name;

  public DessertItem() {
  }; // ?

  public DessertItem(String name) {
    this.name = name;
  }

  public String toString() {
    StringBuilder builder=new StringBuilder();
    int pad=DessertShoppe.PRINT_WIDTH-name.length();
    builder.append(name);
    builder.append(String.format("%"+pad+"s", DessertShoppe.cents2dollars(getCost())));
    return builder.toString();
  };

  // get the price in cent
  public abstract int getCost();

}
