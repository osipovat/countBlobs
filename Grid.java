
package a1;

import java.util.ArrayList;

public class Grid
{
protected int rows;         // number of grid rows
protected int cols;         // number of grid columns
 
protected boolean [][] grid;     // the grid containing blobs

public Grid(int rows, int cols, boolean[][] grid)

{
 this.rows = rows;
 this.cols = cols;
 this.grid = grid;
 
}

public String toString()
{
 String gridString = "";
 for (int i = 0; i < rows; i++)
 {
   for (int j = 0; j < cols; j++)
   {
     if (grid[i][j])
       gridString = gridString + "X";
     else
       gridString = gridString + ".";
    }
   gridString = gridString + "\n";   // end of row
 }  
 return gridString;
}

public int blobCount()
// returns the number of blobs in this grid
{
 int count = 0;
 boolean [][] markedGrid = new boolean[rows][cols]; //the grid that contains marked cells
//Marked cells means:
//1. The location does exist; that is it is not outside the boundaries of the grid.
//2. The location does contain a blob character.
//3. The location has not already been visited.
 for (int m = 0; m < rows; m++)
 {
  for (int n = 0; n < cols; n++)
   {
	  markedGrid[m][n] = false;
   }
   }
 for (int i = 0; i < rows; i++)
  {
   for (int j = 0; j < cols; j++)
    {
      //if it is part of blob that was not yet counted
     if (grid[i][j] && !markedGrid[i][j]){
      ArrayList<Integer> row_values = new ArrayList<Integer>(rows*cols);
      ArrayList<Integer> column_values = new ArrayList<Integer>(rows*cols);
      row_values.add(i);
      column_values.add(j);
      markedGrid[i][j] = true;
      count += 1;
      System.out.println("row: " + i);
      System.out.println("column: " + j);
      //check and mark all the cells close to the 'X' cell
      while (!row_values.isEmpty()){
    	  
        int r = row_values.get(0);
        int c = column_values.get(0);
        row_values.remove(0);
        column_values.remove(0);
        if ((r-1) >= 0 && (c-1) >= 0 && !markedGrid[r-1][c-1] && grid[r-1][c-1]){
          markedGrid[r-1][c-1] = true;
          row_values.add(r-1);
          column_values.add(c-1);
        }
        if ((c-1) >=0 && !markedGrid[r][c-1] && grid[r][c-1]){
          markedGrid[r][c-1] = true;
          row_values.add(r);
          column_values.add(c-1);
        }
        if ((c-1) >=0 && (r+1) < rows && !markedGrid[r+1][c-1] && grid[r+1][c-1]){
          markedGrid[r+1][c-1] = true;
          row_values.add(r+1);
          column_values.add(c-1);
        }
        if ((r+1) < rows && !markedGrid[r+1][c] && grid[r+1][c]){
          markedGrid[r+1][c] = true;
          row_values.add(r+1);
          column_values.add(c);
        }
        if ((r+1) < rows && (c+1) < cols && !markedGrid[r+1][c+1] && grid[r+1][c+1]){
          markedGrid[r+1][c+1] = true;
          row_values.add(r+1);
          column_values.add(c+1);
        }
        if ((c+1) < cols && !markedGrid[r][c+1] && grid[r][c+1]){
          markedGrid[r][c+1] = true;
          row_values.add(r);
          column_values.add(c+1);
        }
        if ((r-1) >= 0 && (c+1) < cols && !markedGrid[r-1][c+1] && grid[r-1][c+1]){
          markedGrid[r-1][c+1] = true;
          row_values.add(r-1);
          column_values.add(c+1);
        }
        if ((r-1) >= 0 && !markedGrid[r-1][c] && grid[r-1][c]){
          markedGrid[r-1][c] = true;
          row_values.add(r-1);
          column_values.add(c);
        }
       }
      
      }
     }
   }
 return count;
}
}
