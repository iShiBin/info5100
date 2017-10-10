package assignment5;

import java.text.DecimalFormat;

public class DessertShoppe {
  final static float TAX_RATE=0.065f;

  final static String NAME="M & M Dessert Shoppe";
  final static int ITEM_NAME_MAX_SIZE=25;
  final static int PRINT_WIDTH=33;
  
  final static DecimalFormat currencyFormat=new DecimalFormat("#.00");
  
  static String cents2dollars (int cent){
    return currencyFormat.format(1.0*cent/100);
  }
}

