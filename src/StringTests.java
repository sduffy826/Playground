
import java.util.*;
import java.io.*;

public class StringTests {
  
    private static String onlyAlpha(String theStr) {
      return theStr.replaceAll("[^a-z]","");
    }
    private static boolean isPalendrom(String string2Check) {
      String tempStr = string2Check.toLowerCase().replaceAll("[^a-z]","");
      int strLen = tempStr.length();
      int midPt = (strLen+1) / 2;
      for (int i = 0; i < midPt; i++) {
        // Compare char at i to (end-i)  (strLen is one past end)
        if (tempStr.charAt(i) != tempStr.charAt((strLen-1)-i)) {
          return false;  
        }        
      }
      return true;      
    }
  
	public static void main(String args[])
	{
	  String theName = "Duffy, S P. (Seany) *CONTRACTOR*";
	  String temp[]  = theName.split(",");
	  String firstName, lastName;	 
	  
	  String theone = "andd 4 n2 *a";
	  
	  System.out.println(onlyAlpha(theone));
	  
	  if (isPalendrom(theone)) {
	    System.out.println("Is a palendrome");
	  }
	  else System.out.println("Is not a palendrome");
	    
	  if ( temp.length > 0 ) { 
	    lastName = temp[0];
	    if ( temp.length == 2 ) {
	      String temp2[] = temp[1].split("\\*");
	      firstName = temp2[0].trim();
	      if (firstName.indexOf('(') != 0) {
	        String temp3[] = firstName.split("\\("); 
	        firstName = temp3[0].trim();
	      }
	      int thePos = firstName.indexOf('.');
	      if ( ( thePos > 2 ) && ( firstName.charAt(thePos-2) == ' ' ) ) {
	       
	        for (int jjj = thePos; jjj > 0; jjj--) 
	          System.out.println(firstName.charAt(jjj));
	        firstName = firstName.substring(0,thePos-2);
	        System.out.println("Firstname: " + firstName);
	      }
	    }
	  }
	}
}
