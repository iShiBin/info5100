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
 * @author bin
 */
class Partition {
  public boolean findPartition(int arr[]) {
    long sum = IntStream.of(arr).asLongStream().sum();

    // sum is not even, return false
    if (sum % 2 == 1)
      return false;

    long target = (sum >> 1); // the target is to find a group of number which
                              // sum to half
    Arrays.sort(arr);
    return findPartition(arr, arr.length - 2, target - arr[arr.length - 1]);
  }

  private boolean findPartition(int[] nums, int index, long target) {
    if (target == 0) {
      return true;
    }
    if (target < 0 || index < 0) {
      return false;
    }
    // if the biggest is not the target, then find it in the smaller numbers or
    // find the remainder in the smaller numbers.
    return findPartition(nums, index - 1, target - nums[index]) || findPartition(nums, index - 1, target);
  }

  public static void main(String[] args) {
    int[] nums = { 1, 5, 11, 5 };
    System.out.println(new Partition().findPartition(nums));

    nums = new int[] { 1, 5, 3 };
    System.out.println(new Partition().findPartition(nums));

    nums = new Random().ints(100, 1, 1000).toArray();
    System.out.println(new Partition().findPartition(nums));
  }
}
