/* Good Work
 * Score 2 + extra credit 2; Total score 10
 */
package assignment7;

import java.util.*;
import java.util.stream.*;

/**
 * 5. Determine whether a given array (positive integers) can be partitioned
 * into two subsets such that the sum of elements in both subsets is same.(score 2)
 * 
 * Examples arr[] = {1, 5, 11, 5} Output: true The array can be partitioned as
 * {1, 5, 5} and {11} arr[] = {1, 5, 3} Output: false The array cannot be
 * partitioned into equal sum sets
 * 
 * Note: The numbers could be negative.
 * 
 * @author bin
 */
class Partition { // extra credit 2
  public boolean findPartition(int arr[]) {
    if(arr==null || arr.length<2) return false;
    
 // the target is to find a group of number which sum to half
    long sum = IntStream.of(arr).asLongStream().sum();
    
//    if sum is odd, there can not be two subsets with equal sum
    if(sum%2==1) return false;
    
    return findPartition(arr, arr.length-1, sum>>1);
  }

  private boolean findPartition(int[] nums, int tail, long target) {
    if (target == 0) return true;
    if (tail < 0 ) return false;

    // if the biggest is not the target, then find it in the smaller numbers or
    // find the remainder in the smaller numbers.
    return findPartition(nums, tail - 1, target - nums[tail]) || findPartition(nums, tail - 1, target);
  }

//  main function to test/verify
  public static void main(String[] args) {
    int[] nums = { 1, 5, 11, 5 };
    System.out.println(new Partition().findPartition(nums));

    nums = new int[] { 1, 5, 3 };
    System.out.println(new Partition().findPartition(nums));
    
    nums = new int[]{-1,-2,-3, -2};
    System.out.println(new Partition().findPartition(nums));
    
//    generate an integer array with 100 numbers range from -100 to 100
    nums = new Random().ints(100,-100, 100).toArray();
    System.out.println(new Partition().findPartition(nums));
  }
}
