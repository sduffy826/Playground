package calendarPackage;

import java.util.*;

/**
 * Examples of java date/calendar routines
 * 
 */
public class CalendarTest
{
  public static final boolean DEBUG = false;
  
  private static void showMillisInHrsMins(long millis) {
    // Show the given milliseconds in hrs and minutes
    long mins = java.lang.Math.abs((millis / (60 * 1000)) % 60);
    long hrs = millis / (60 * 60 * 1000);
    if (DEBUG) System.out.format("Hrs:Min: %1$d:%2$02d\n", hrs, mins);
  }
  
  private static Calendar convertCal2Zone(final Calendar from, final TimeZone targetZone) {
    // Convert a given calendar object from one timezone to another
    long millis = from.getTimeInMillis();
    if (DEBUG) { System.out.print("Millis in source: "); System.out.println(millis); }
      
    Calendar target = new GregorianCalendar(targetZone);
    target.setTimeInMillis(millis);
    if (DEBUG) System.out.println(target.getTimeInMillis());
    return target;
  }
   
  private static Calendar convertCal(final Calendar from, final TimeZone targetZone) {  
    // NOTE: THIS does not work because of daylight savings changes the from and too
    //       have different offsets during daylight savings changes, use convertCal2
    //       Left here just to show
    //
    // Convert a calendar from one timezone, to the same time in another timezone, note
    // this uses milliseconds so doesn't work properly during daylight savings changes
    long millis = from.getTimeInMillis();
    if (DEBUG) { System.out.print("Millis in source: "); System.out.println(millis); }
    
    int baseOffset = from.getTimeZone().getOffset(millis);
    if (DEBUG) { 
      System.out.print("BaseOffset in source: ");  
      System.out.println(baseOffset);
      showMillisInHrsMins(baseOffset);
    }
    
    Calendar target = new GregorianCalendar(targetZone);
    if (DEBUG) {
      System.out.println("");
      System.out.print("Target: ");
      System.out.println(target.toString());
    }
    
    // Print out the target information
    int offset = targetZone.getOffset(millis);
    if (DEBUG) {
      System.out.print("targetOffset: "); 
      System.out.println(offset);
      showMillisInHrsMins(offset);
      showMillisInHrsMins(baseOffset - offset);
    }
    
    target.setTimeInMillis(millis+(baseOffset - offset));
    if (DEBUG) {
      System.out.print("targetMillis: "); 
      System.out.println(target.getTimeInMillis());
    }
    return target;
  }
  
  private static Calendar convertCal2(final Calendar from, final TimeZone targetZone) {    
    // Convert a calendar from one timezone, to the same time in another timezone, this
    // just calls the setter with the 'from' gets     
    Calendar target = new GregorianCalendar(targetZone);
    target.set(from.get(Calendar.YEAR), from.get(Calendar.MONTH), from.get(Calendar.DATE),
               from.get(Calendar.HOUR), from.get(Calendar.MINUTE), from.get(Calendar.SECOND));
    if (DEBUG) {
      System.out.println("");
      System.out.print("Target: ");
      System.out.println(target.toString());
    }
    
    return target;
  }
    
  public static Calendar convertCalendar(final Calendar calSource, final TimeZone timeZoneTarget) {
    // Create calendar object with target timezone, this suffers same problems
    // as convertCal, so DON'T USE unless to educate yourself
    Calendar target = new GregorianCalendar(timeZoneTarget);
      
    target.setTimeInMillis(calSource.getTimeInMillis() + 
      calSource.getTimeZone().getOffset(calSource.getTimeInMillis()) -
      timeZoneTarget.getOffset(calSource.getTimeInMillis()));
    target.getTime();  // Old jave had problem and required call to getTime to correct
    return target; 
  } 

  private static void sampleCode() {
    // get a calendar instance at December 31, 2009, at 11:30 p.m.
    // this way we can test that we are rolling over to the next hour,
    // tomorrow, next week, and next year properly.
    Calendar calendar = new GregorianCalendar(2009, 11, 31, 23, 30, 0);
    long getMilli = calendar.getTimeInMillis();
    System.out.println(getMilli);
    System.out.println(calendar.toString());
    
    String s = String.format("Before: %1$tB %1$td,%1$tY at: %1$tr %1$tZ", calendar);
    System.out.println(s);
    
    TimeZone dflt = java.util.TimeZone.getTimeZone("PST");
    Calendar newTime = convertCal(calendar, dflt);
    System.out.println(newTime.toString());
    String ss = String.format("After: %1$tB %1$td,%1$tY at: %1$tr %1$tZ", newTime);
    System.out.println(ss);
    System.out.println("--------------------------------------");
    
    Calendar gmtTime = new GregorianCalendar(java.util.TimeZone.getTimeZone("GMT"));
    gmtTime.setTimeInMillis(getMilli);
    System.out.println(gmtTime.getTimeInMillis());
    
    // get a Date instance to represent "now" (the current date);
    // we'll need it to reset our calendar during the following date examples.
    Date currentDate = calendar.getTime();
    System.out.format("today:      %s\n", currentDate);
    Date anotherDate = gmtTime.getTime();
    System.out.format("newToday:   %s\n", anotherDate);
 
    // get the date/time one hour from now
    calendar.setTime(currentDate);
    calendar.add(Calendar.HOUR_OF_DAY, 1);
    Date oneHour = calendar.getTime();
    System.out.format("one hour:   %s\n", oneHour);
    
    // get tomorrow's date
    calendar.setTime(currentDate);
    calendar.add(Calendar.DAY_OF_YEAR, 1);
    Date tomorrow = calendar.getTime();
    System.out.format("tomorrow:   %s\n", tomorrow);
    
    // get next week's date
    // note: may want to use WEEK_OF_MONTH or WEEK_OF_YEAR
    calendar.setTime(currentDate);
    calendar.add(Calendar.DAY_OF_YEAR, 7);
    Date nextWeek = calendar.getTime();
    System.out.format("next week:  %s\n", nextWeek);
  
    // get next month
    calendar.setTime(currentDate);
    calendar.add(Calendar.MONTH, 1);
    Date nextMonth = calendar.getTime();
    System.out.format("next month: %s\n", nextMonth);
  
    // get next year
    calendar.setTime(currentDate);
    calendar.add(Calendar.YEAR, 1);
    Date nextYear = calendar.getTime();
    System.out.format("next year:  %s\n", nextYear);
  }
  
  private static void dumpIt(List<Calendar> theList) {
    int spaces = 0;
    for (Object o : theList) {
      for (int i = 0; i < spaces; i++) System.out.print(" ");
      String s = String.format("Before: %1$tB %1$td,%1$tY at: %1$tr %1$tZ", o);
      System.out.println(s);
      spaces += 4;
    }
  }
  
  public static void main(String[] args)
  {
//    sampleCode();
    
    Calendar theCal;
    List<Calendar> calList = new ArrayList<Calendar>();
    List<Calendar> tempList = new ArrayList<Calendar>();
    TimeZone testTZ = java.util.TimeZone.getTimeZone("PST");
    
    calList.add(new GregorianCalendar(2012, 02, 11, 00, 59, 0));  
    calList.add(new GregorianCalendar(2012, 10, 04, 00, 59, 0));
    
    for (Iterator<Calendar> it = calList.iterator(); it.hasNext();) {
      Calendar theObj = it.next();
    
      for (int j = 1; j < 4; j++) {
        tempList.clear();
        theObj.add(Calendar.HOUR, 1);
        tempList.add(theObj);
        tempList.add(convertCal(theObj,testTZ));
        tempList.add(convertCal2(theObj,testTZ));
        tempList.add(convertCalendar(theObj,testTZ));
        tempList.add(convertCal2Zone(theObj,testTZ));
        dumpIt(tempList);
      }
    }
  }
}
