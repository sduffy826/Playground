package com.corti.suduko;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SolveBoard {

  static final boolean debugIt = false;
  static final boolean debugIt2 = false;
  
  Board board = null;
  int boardMatrixSize;
  Stack<Board> boards2Play = null;
  Stack<Board> boardSolutions = null;
  
  // Constructor where initial board is passed in
  public SolveBoard(Board board) {
    this.board = board;
    initAndProcess();
  }
  
  // Constructor where pass in a file (csv) that has the values for the puzzle
  public SolveBoard(String fileName) {
    board = new Board();
    initBoardPositionsFromFile(fileName);
    initAndProcess();
  }

  private void initAndProcess() {
    boardMatrixSize = board.getMATRIXSIZE();
    boards2Play = new Stack();
    boardSolutions = new Stack();

    // Push board onto stack
    boards2Play.push(board);
    
    processBoard();
  }
    
  // Write out all the solutions to the console  
  public void writeSolutions() {
    System.out.println("In writeSolutions, there are: " + boardSolutions.size() + " solutions");
    
    while (boardSolutions.isEmpty() == false) {
      board = boardSolutions.pop();
      board.dumpValues();
    }    
  }
  
  // This finds all the solutions for a board, the solutions are added to the stack boardSolutions
  // How it works, it pops a board of the stack of boards to try, it gets the unassigned row/column
  // positition with the least available numbers that can be assigned, it pushes a new board onto
  // the stack with each of those numbers, it repeats the process till there are no more boards on
  // the stack... this way every solution available (if more than one) is in the stack of boardSolutions
  private void processBoard() {
    if (debugIt) System.out.println("In processBoard, playing boards");
    
    while (boards2Play.isEmpty() == false) {
      board = boards2Play.pop();  // Get board to play
      if (debugIt) {
        System.out.println("Playing board, there are: " + boards2Play.size() + " boards still to look at");
        board.dumpValues();
      }
      if (board.isBoardFull()) {
        if (debugIt) System.out.println("**************** a SOLUTION FOUND ***********************");
        boardSolutions.push(board);
      }
      else {
        int minRowPos = 0, minColPos = 0;
        List<Integer> holdNumberOfPositionsAvailable = null;
        int minNumPositionsAvailable = boardMatrixSize;      
      
        // Loop thru till we have a solution, or can't find one.    
        for (int rowIndex = 0; rowIndex < boardMatrixSize; rowIndex++) {
          for (int colIndex = 0; colIndex < boardMatrixSize; colIndex++) {
            if (board.isPositionAssigned(rowIndex,  colIndex) == false) {
              if (debugIt2) System.out.println("Value not assigned to: " + rowIndex + "/" + colIndex);
              
              List<Integer> numberOfPositionsAvailable = board.numsAvailable(rowIndex, colIndex);
              if (debugIt2) System.out.println("Potential values for: " + rowIndex + "/" + colIndex + " is: " + numberOfPositionsAvailable.size());
      
              if (numberOfPositionsAvailable.size() < minNumPositionsAvailable) {
                // Save position, this row/col has min number of potential numbers that can be assigned
                minRowPos = rowIndex;
                minColPos = colIndex;
                minNumPositionsAvailable = numberOfPositionsAvailable.size();
                holdNumberOfPositionsAvailable = new ArrayList<Integer>(numberOfPositionsAvailable);
              }
            }
          }
        }
      
        // We want to try boards with each of the available positions
        if (debugIt) {
          System.out.println("Slot: " + minRowPos + "/" + minColPos + " has potential of: " +
                             holdNumberOfPositionsAvailable.size() + " we will try each of those");
        }
        for (int i = 0; i < holdNumberOfPositionsAvailable.size(); i++) {
          Board newBoard = new Board(board); // Get new board with existing boards values.
          newBoard.setBoardValue(minRowPos,  minColPos,  holdNumberOfPositionsAvailable.get(i));
          boards2Play.push(newBoard);
        }
      }    
    }   
  }
    
  // Read the csv file with the initial board positions
  private void initBoardPositionsFromFile(String fileName) {
    String line = "";
    int rowPos = 0;
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        int colPos = 0;
        for (String aValue : values) {
          if (aValue.trim().length() > 0 ) {
            int valueInt = Integer.parseInt(aValue);
            board.setBoardValue(rowPos,  colPos,  valueInt);
            if (debugIt) System.out.println("Got value: " + aValue + " at position: " + rowPos + "/" + colPos);
          }
          colPos++;
        }
        rowPos++;
      }
    } catch (FileNotFoundException e1) {
        e1.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    /*    
    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
      //stream.forEach(System.out::println);
      List<String> colValues = stream
                               .findFirst()
                               .map((line) -> Arrays.asList(line.split(",")))
                               .get();
      System.out.println("colValue: " + colValues);
      
      
      
    } catch (IOException e) {
      e.printStackTrace();
    } 
    */   
  }
}
