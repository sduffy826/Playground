package com.corti.suduko;

// Little stub to test a suduko puzzle.
public class TestSudukoPuzzle {

  static SolveBoard solveBoard = null;
  static String fileNameWithPuzzle = "suduko.csv";
  
  public static void main(String[] args) {    
    if (args.length > 0) {
      fileNameWithPuzzle = args[0];
    }
    solveBoard = new SolveBoard(fileNameWithPuzzle);
    solveBoard.writeSolutions();
  }
  
}
