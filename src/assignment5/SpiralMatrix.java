package assignment5;

import java.util.*;

public class SpiralMatrix { // extra credit 2
  private List<Integer> list = new ArrayList<Integer>();

  public List<Integer> spiralOrder(int[][] matrix) {
    if (matrix == null)
      return null;
    if (matrix.length < 1)
      return new ArrayList<>();

    spiralOrder(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1);
    return list;
  }

  private void spiralOrder(int[][] matrix, int r1, int c1, int r2, int c2) {
    if (r1 > r2 || c1 > c2)
      return;
    else if (r1 == r2) {
      list.addAll(copyRow(matrix, r1, c1, c2));
      return;
    } else if (c1 == c2) {
      list.addAll(copyColumn(matrix, c2, r1, r2));
      return;
    } else {

      list.addAll(copyRow(matrix, r1, c1, c2));
      if (r1 + 1 <= r2 - 1)
        list.addAll(copyColumn(matrix, c2, r1 + 1, r2 - 1));
      list.addAll(copyRow(matrix, r2, c2, c1));
      if (r2 - 1 >= r1 + 1)
        list.addAll(copyColumn(matrix, c1, r2 - 1, r1 + 1));

      spiralOrder(matrix, r1 + 1, c1 + 1, r2 - 1, c2 - 1);
    }
  }

  // start and end are the column index
  private List<Integer> copyRow(int[][] matrix, int row, int start, int end) {
    List<Integer> l = new ArrayList<>();
    if (start <= end) {
      for (int j = start; j <= end; j++)
        l.add(matrix[row][j]);
    } else {
      l = copyRow(matrix, row, end, start);
      Collections.reverse(l);
    }
    // System.out.println("Row Copied"+l);
    return l;
  }

  // start and end are the row index
  private List<Integer> copyColumn(int[][] matrix, int col, int start, int end) {
    List<Integer> l = new ArrayList<>();
    if (start <= end) {
      for (int i = start; i <= end; i++) {
        l.add(matrix[i][col]);
      }
    } else {
      l = copyColumn(matrix, col, end, start);
      Collections.reverse(l);
    }
    // System.out.println("Col Copied"+l);
    return l;
  }

  public static void main(String[] args) {
    int[][] matrix = { { 1 } };
    System.out.println(new SpiralMatrix().spiralOrder(matrix));
    // [1]

    matrix = new int[][] { { 1, 2 } };
    System.out.println(new SpiralMatrix().spiralOrder(matrix));
    // [1, 2]

    matrix = new int[][] { { 1 }, { 2 } };
    System.out.println(new SpiralMatrix().spiralOrder(matrix));
    // [1, 2]

    matrix = new int[][] { { 1, 2 }, { 3, 4 } };
    System.out.println(new SpiralMatrix().spiralOrder(matrix));
    // [1, 2, 4, 3]

    matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    System.out.println(new SpiralMatrix().spiralOrder(matrix));
    // [1, 2, 3, 6, 9, 8, 7, 4, 5]

    matrix = new int[][] { { 1, 2, 3, 10 }, { 4, 5, 6, 11 }, { 7, 8, 9, 12 }, { 14, 15, 16, 17 } };
    System.out.println(new SpiralMatrix().spiralOrder(matrix));
    // [1, 2, 3, 10, 11, 12, 17, 16, 15, 14, 7, 4, 5, 6, 9, 8]
  }
  
  //this is the second solution
  public List<Integer> spiralOrderII(int[][] matrix) {
    if (matrix.length == 0)
      return new ArrayList<>();

    int m = matrix.length, n = matrix[0].length;
    List<Integer> list = new ArrayList<>(m * n);

    int row = 0, col = -1;
    while (true) {
      for (int i = 0; i < n; i++) {
        list.add(matrix[row][++col]);
      }

      if (--m == 0)
        break;
      for (int i = 0; i < m; i++) {
        list.add(matrix[++row][col]);
      }

      if (--n == 0)
        break;
      for (int i = 0; i < n; i++) {
        list.add(matrix[row][--col]);
      }

      if (--m == 0)
        break;
      for (int i = 0; i < m; i++) {
        list.add(matrix[--row][col]);
      }

      if (--n == 0)
        break;
    }
    return list;
  }
}
