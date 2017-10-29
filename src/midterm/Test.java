package midterm;

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


import java.util.ArrayList;


class Test {

  public ArrayList<Cell> findPath(int[][] maze) {
    if(maze==null || maze[0][0]==0) return new ArrayList<Cell>();
    
    ArrayList<Cell> path=new ArrayList<Cell>();
    findPath(maze, 0, 0, path);
    return found?path:new ArrayList<Cell>();
  }
  
  private boolean found=false;
  private void findPath(int[][] m, int x, int y, ArrayList<Cell> list){
    list.add(new Cell(x,y));
    if(x==m.length-1 && y==m[x].length-1){
      found=true;
      return ;
    }
    
    if (!found && x < m.length - 1 && m[x + 1][y] == 1)
      findPath(m, x + 1, y, list);
    if (!found && y < m[0].length - 1 && m[x][y+1] == 1)
      findPath(m, x, y + 1, list);
  }
    
  public static void main(String[] args) {
    int[][] m = { { 1, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 1, 0, 0 }, { 1, 1, 1, 1 } };
    Question5 test=new Question5();
    System.out.println(test.findPath(m));
  }

}
