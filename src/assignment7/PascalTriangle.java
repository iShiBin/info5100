package assignment7;
/**
 * 4. Pascal’s triangle is a triangular array of the binomial coefficients.
 * Write a function that takes an integer value n as input and prints first n lines of the Pascal’s triangle.
 * Following are the first 6 rows of Pascal’s Triangle.(Score 2) 
 * 
 */
import java.util.*;

class PascalTriangle { // score 2
  public void printPascalTriangle(int n){
    List<List<Long>> list=generate(n);
    for(List<Long> l:list){
      System.out.println(l);
    }
  }
  
  public List<List<Long>> generate(int n) {
    List<List<Long>> allrows = new ArrayList<List<Long>>();
    List<Long> row = new ArrayList<Long>();
    for (int i = 0; i < n; i++) {
//    [1, 2, 1] becomes to [1, 1, 2, 1] and then calculate the current row
      row.add(0, 1L);
      for (int j = 1; j < row.size()-1; j++)
        row.set(j, row.get(j) + row.get(j + 1));
      allrows.add(new ArrayList<>(row));
    }
    return allrows;
  }
  
  public static void main(String args[]){
    PascalTriangle triangle = new PascalTriangle();
    int n = 10;
    triangle.printPascalTriangle(n);
  }

}
