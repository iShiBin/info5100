// extra credit 5
/**
Extra Credit
A Maze is given as N*N binary matrix of blocks where source block is the upper left most block
i.e., maze[0][0] and destination block is lower rightmost block i.e., maze[N­1][N­1].
A rat starts from source and has to reach destination. The rat can move only in two directions: forward and down.
In the maze matrix, 0 means the block is dead end and 1 means the block can be used in the path from source to destination.

Your function should take the maze as input and return an arrayList of the resulting path. If no path is found return empty list.

Example:
 {1, 0, 0, 0}
 {1, 1, 1, 1}
 {0, 1, 0, 0}
 {1, 1, 1, 1}
 
Output:
 [[0, 0], [1, 0], [1, 1], [2, 1], [3, 1], [3, 2], [3, 3]]
 
 */

package midterm;

import java.util.ArrayList;

class Cell {
  int x, y;

  Cell(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public String toString() {
    return "[" + this.x + ", " + this.y + "]";
  }
}

/*
 * Algorithm: Use recursion to reduce the scale of this problem 1 less each time to a smaller one
 * by move to either forward or down.
 * 
 * Base Cases:
 * 1. return false: the indexes are out of bounder; or the value is 0; or this node has been visited
 * 2. return true: if it reaches the destination at the bottom right corner
 * 
 * Otherwise, try to move the mouse 1 step forward or 1 step down
 */

/*

for input = { {1,0,0,1,0},
              {1,1,1,1,1},
              {1,0,0,1,0},
              {1,1,0,1,1},
              {0,0,0,0,1} };
             
   expected output: [[0, 0], [1, 0], [1, 1], [1, 2], [1, 3], [2, 3], [3, 3], [3, 4]], [4, 4]
   your output    : [[0, 0], [1, 0], [2, 0], [3, 0], [3, 1], [1, 1], [1, 2], [1, 3], [2, 3], [3, 3], [3, 4], [4, 4]]
*/
class Question5 {
  public ArrayList<Cell> findPath(int[][] maze) {
    if(maze==null || maze.length==0 || maze[0][0]==0) return new ArrayList<Cell>();
    ArrayList<Cell> path=new ArrayList<Cell>();
    isVisited=new boolean[maze.length][maze[0].length];
    boolean found=findPath(maze, 0, 0, path);
    return found?path:new ArrayList<Cell>();
  }
  
  private boolean[][] isVisited;
  private boolean findPath(int[][] m, int x, int y, ArrayList<Cell> list) {

    if (x > m.length - 1 || y > m[x].length - 1 || m[x][y] == 0 || isVisited[x][y]){
      return false; // illegal cases or visited spots
    }

    list.add(new Cell(x, y));
    isVisited[x][y]=true;
    if (x == m.length - 1 && y == m[x].length - 1)
      return true; // reach to the destination
    
    if(findPath(m, x + 1, y, list)) return true;
    if(findPath(m, x, y + 1, list)) return true;
    
    list.remove(list.size()-1); //backtracking
    return false;
    }
  

  public static void main(String[] args) {
    int[][] m = { { 1, 0, 0, 1, 0 }, { 1, 1, 1, 1, 1 }, { 1, 0, 0, 1, 0 }, { 1, 1, 0, 1, 1 }, { 0, 0, 0, 0, 1 } };
    Question5 test=new Question5();
    System.out.println(test.findPath(m));
    
  }

}
