import java.text.DecimalFormat;

public class TestArea {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    String oldString = "The next \"string is quoted\"";
    // Since using regex need four \ to escape one (you'd think that \\\" would
    // replace " with \" but regex needs \\\\ to get \\ as escape sequence
    String newString = oldString.replaceAll("\"", "\\\\\"");
    System.out.println("Old:" + oldString + ": New:" + newString + ":");
    
    double aDouble = Math.abs(-1.00 / 100000.0);
    System.out.println(aDouble);
    
    DecimalFormat df = new DecimalFormat("##0.0000%");
    System.out.println(df.format(aDouble));

    String testNull = " ";
    if (testNull.isEmpty()) { 
      System.out.println("Is empty");
    }
    else
    if (testNull.trim().length() == 0) {
      System.out.println("Length is zero");
    }
    
    
    String theVar = "#Hi Sean After Pound";
    System.out.println("Before:" + theVar + ":");
    theVar = theVar.split("#")[0];
    System.out.println("After:" + theVar + ":");
    
  }

}
