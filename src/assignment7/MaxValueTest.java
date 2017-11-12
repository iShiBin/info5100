package assignment7;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;

public class MaxValueTest {
  
  private int[] smallCase=MaxValue.populateData(1000);
  private int[] bigCase=MaxValue.populateData(1000000);

  @Test
  public void testSmallCase() throws InterruptedException {
    MaxValue.findMax(smallCase);
  }
  
  @Test
  public void testSmallCaseStream() throws InterruptedException {
    IntStream.of(smallCase).max();
  }
  
  @Test
  public void testBigCase() throws InterruptedException {
    MaxValue.findMax(bigCase); // 2/3 time to find the max of big cases 1M
  }
  
  @Test
  public void testBigCaseStream() throws InterruptedException {
    IntStream.of(bigCase).max();
  }

}
