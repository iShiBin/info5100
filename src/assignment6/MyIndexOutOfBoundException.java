package assignment6;

import java.util.Random;

class MyIndexOutOfBoundException extends RuntimeException { // score 2
  int lowerBound, upperBound, index;

  MyIndexOutOfBoundException() {
  }

  MyIndexOutOfBoundException(int current, int lowest, int highest) {
    this.index = current;
    this.lowerBound = lowest;
    this.upperBound = highest;
  }

  public String toString() {
    return "Error Message: Index: " + index + ", but Lower bound: " + this.lowerBound + ", Upper Bound: "
        + this.upperBound;
  }

  public static void main(String[] args) {
    final int SIZE = 100;
    int[] nums = new int[SIZE];

    Random random = new Random();

    System.out.println("Starting random access in an int array with size " + SIZE + " ...");
    while (true) {
      int i = random.nextInt(SIZE + SIZE / 2);
      try {
        System.out.println("Try to access the number with index: " + i);
        if (i >= SIZE)
          throw new MyIndexOutOfBoundException(i, 0, SIZE - 1);
        else
          nums[i] = random.nextInt(SIZE);
      } catch (Exception ex) {
        System.out.println(ex);
        return;
      }
    }
  }
}
