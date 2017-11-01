import static org.junit.Assert.*;

import org.junit.Test;

public class FinalTest {

  @Test
  public void test() {
    final int somethingLast;
    somethingLast=1;
//    somethingLast=2; // this won't compile
  }

}
