package com.corti.suduko;

import java.util.ArrayList;
import java.util.List;

public class Board {
  private int[][] board;
  private static final int NOTASSIGNED = 0;
  private static final int MATRIXSIZE = 9;
  private static final int SQRT_MATRIXSIZE = (int) Math.sqrt(MATRIXSIZE);
  private int numbersAssigned;
    
  // Constructor without args
  public Board() {
    board = new int[MATRIXSIZE][MATRIXSIZE];
    initBoard();
  }
  
  // Create board from existing board
  public Board(Board existingBoard) {
    board = new int [MATRIXSIZE][MATRIXSIZE];
    for (int x = 0; x < MATRIXSIZE; x++) {
      for (int y = 0; y < MATRIXSIZE; y++) { 
        board[x][y] = existingBoard.getBoardValue(x,y);
      }
    }
    numbersAssigned = existingBoard.getNumbersAssigned();
  }  
  
  // Initialize board, basically set all values to NOTASSIGNED
  private void initBoard() {
    for (int x = 0; x < MATRIXSIZE; x++) {
      for (int y = 0; y < MATRIXSIZE; y++) { 
        board[x][y] = NOTASSIGNED;
      }
    }    
    numbersAssigned = 0;
  }

  // Set board positions value, we don't check for range, let it throw exception :)
  public void setBoardValue(int row, int col, int value) {
    board[row][col] = value;
    if (value != NOTASSIGNED) numbersAssigned++;
  }  

  // Get value for board position
  public int getBoardValue(int row, int col) {
    return board[row][col];
  }  
 
  // Return number of values defined in the board
  public int getNumbersAssigned() {
    return numbersAssigned;
  }
  
  // Get board matrix size
  public int getMATRIXSIZE() {
    return MATRIXSIZE;
  }
  
  // Return boolean identifying if all numbers in the board have been assigned.
  public boolean isBoardFull() {
    return (numbersAssigned == (MATRIXSIZE * MATRIXSIZE));
  }
  
  // Return boolean identifying if particular position is assigned a value
  public boolean isPositionAssigned(int row, int col) {
    return (board[row][col] != NOTASSIGNED);
  }
    
  // Return a List of Integers that are the numbers that can be assigned to a
  // particular position.  Note, if the row/col passed in already has a value
  // then this value is not in the list (it's considered used, even though it's
  // used at that row/col).  
  public List<Integer> numsAvailable(int row, int column) {
    List<Integer> rtnList = new ArrayList<>();
    boolean[] availNums = new boolean[MATRIXSIZE];
    for (int i = 0; i < MATRIXSIZE; i++) availNums[i] = true;  // Initialization
    
    // Check all the values in the 'row'
    for (int col2Check = 0; col2Check < MATRIXSIZE; col2Check++) {
        int theNum = board[row][col2Check];
        if (theNum != NOTASSIGNED) {
          availNums[theNum-1] = false;
        }
    }
    
    // Now check all the column values
    for (int row2Check = 0; row2Check < MATRIXSIZE; row2Check++) {
        int theNum = board[row2Check][column];
        if (theNum != NOTASSIGNED) {
          availNums[theNum-1] = false;
        }
    }
    
    // Now check the local quadrant, this is the sub-matrix 
    int quadrantRowStart = (row / SQRT_MATRIXSIZE) * SQRT_MATRIXSIZE;    // Get start of quadrant
    int quadrantColStart = (column / SQRT_MATRIXSIZE) * SQRT_MATRIXSIZE;
    for (int row2Check = quadrantRowStart; row2Check < (quadrantRowStart + SQRT_MATRIXSIZE); row2Check++) {
      for (int col2Check = quadrantColStart; col2Check < (quadrantColStart + SQRT_MATRIXSIZE); col2Check++) {
          int theNum = board[row2Check][col2Check];
          if (theNum != NOTASSIGNED) {
            availNums[theNum-1] = false;
          }
        }
    }
    
    // Now build array of available numbers
    for (int i = 0; i < MATRIXSIZE; i++) {
      if (availNums[i]) {
        rtnList.add(i+1);
      }
    }
    return rtnList;    
  }

  // Dump the board to the console
  public void dumpValues() {
    String sepSingle = new String(new char[(MATRIXSIZE*4)+1]).replace("\0","-");
    String sepDouble = new String(new char[(MATRIXSIZE*4)+1]).replace("\0","=");
    for (int i = 0; i < MATRIXSIZE; i++) {
      System.out.println(i % SQRT_MATRIXSIZE == 0 ? sepDouble : sepSingle);  // Output double sep at quadrant rows
      for (int j = 0; j < MATRIXSIZE; j++) {
        System.out.print("|" + (board[i][j] == NOTASSIGNED ? "  " : String.format("%2d", board[i][j])) + " ");
      }
      System.out.println("|");
    }
    System.out.println(sepDouble);
  }  
}
