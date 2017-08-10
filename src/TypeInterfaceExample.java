
public class TypeInterfaceExample {
  
  // Method that takes an AddInts interface 
  public static int example(AddInts addInts) {      
    return addInts.add(3,  4);
  }
  
  public static void main(String[] args) {
    // Show two lambda expressions, one to prevent all the typing with println
    OutAlias s = (String outMsg) -> System.out.println(outMsg);
    StringLengthLambda myLambda = (String str2Check) -> str2Check.length();
    
    String myString = "today is a great day";
    s.out("Length of: '" + myString + "' is: " + Integer.toString(myLambda.getLength(myString)) );
    
    // show how to pass the lambda as expression to method that takes the proper interface
    int total = example((int a, int b) -> a + b );
    System.out.println("The total: " + total);
    
    // Example of thread with annoymous inner class
    Thread myThread = new Thread(new Runnable() {
      @Override
      public void run() {
        // TODO Auto-generated method stub
        System.out.println("hi there from annonymous inner class");
      }      
    });
    myThread.run();
    
    // Another version, we pass the run implementatin here, cool
    Thread myLambdaThread = new Thread( () -> System.out.println("In lambda runnable"));
    myLambdaThread.run();
    
    
  }

  interface StringLengthLambda {
    int getLength(String s);
  }
  
  interface OutAlias {
    void out(String s);
  }
  
  interface Greeting {
     void greet();
  }
  
  interface AddInts {
    int add(int a, int b);
  }
  
}
