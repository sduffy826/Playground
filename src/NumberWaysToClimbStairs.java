import java.util.Arrays;

/**
 * 
 * @author sduffy
 * @category Java program to calculate the number of ways to climb stairs, things to note
 *   validSteps are the number of steps you can climb at a time 1,2,4 means you can climb one step at a time,
 *     2 steps at a time, or take a really big step and climb 4 at a time.
 *   so you can climb 3 steps by taking path [1,1,1] single step each time, or [1,2] or [2,1] make cents?
 */

public class NumberWaysToClimbStairs {
  static final boolean DEBUGIT = false; 
  public static void main(String[] args) {
    NumberWaysToClimbStairs myObj = new NumberWaysToClimbStairs();
    myObj.runIt();
  }
  
  public void runIt() {
    int[] validSteps = {1, 3, 4};  // Lists number of steps you can take at a time
    // For testing show climbing calculation for differnt steps.
    
    System.out.println("Possible steps to take at a time: " + Arrays.toString(validSteps));
    
    for (int numStairs = 1; numStairs < 6; numStairs++) {
      System.out.println("\nDoing " + Integer.toString(numStairs) + " steps");
      System.out.println("  totals ways to climb them: " + Integer.toString(climbStairs(numStairs,validSteps,"")));
    }
  }  
  
  private int climbStairs(int stairsRemaining, int[] stairIncrements, String pathHere) {
    if ( DEBUGIT ) System.out.println("Called climbStairs with " + Integer.toString(stairsRemaining) + " stairs remaining");
    int paths = 0;
    for (int i = 0; i < stairIncrements.length; i++) {
      String path2ThisStep = pathHere + "," + Integer.toString(stairIncrements[i]);
      if ( stairsRemaining == stairIncrements[i] ) {
        System.out.println("Path: " + path2ThisStep.substring(1));  // Strip leading comma
        paths = paths + 1;
      }
      else if ( stairsRemaining >= stairIncrements[i] ) {
        paths = paths + climbStairs(stairsRemaining - stairIncrements[i], stairIncrements, path2ThisStep);
      }
    }    
    return paths;
  }
}
