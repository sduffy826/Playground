
public class TestTimestamp {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    
     // 1) create a java calendar instance
    java.util.Calendar calendar = java.util.Calendar.getInstance();

    System.out.println(calendar.toString());
    // 2) get a java.util.Date from the calendar instance.
    //        this date will represent the current instant, or "now".
    java.util.Date now = calendar.getTime();
    System.out.println(now.toString());
    
    // 3) a java current time (now) instance
    java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
    System.out.println(currentTimestamp);
    
    java.sql.Timestamp currTS = new java.sql.Timestamp(java.util.Calendar.getInstance().getTime().getTime());
    System.out.println("currTS:" + currTS);
   
  }

}
