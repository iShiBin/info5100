package midterm;

public class InfinityTest {

  public static void main(String[] args) {
    int x=3;
    float y=0;
    
    System.out.println(y);
    System.out.println(x/y);
    System.out.println(3/0f);
    
    double inf = Double.POSITIVE_INFINITY;
    System.out.println(inf + 5); // Infinity
    System.out.println(inf - inf); // same as Double.NaN
    System.out.println(inf * -1); // same as Double.NEGATIVE_INFINITY
    
  }

}
