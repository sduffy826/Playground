package com.corti.lamdaexercise;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Unit1Exercise {

  public static void main(String[] args) {
    // Define lambda's
    MyPersonInterface printAll = listOfPeople -> { 
      for (Person aPerson: listOfPeople) { 
        System.out.println(aPerson.toString()); 
      }
    };

    // Define comparator
    Comparator<Person> compIt = (Person lhs, Person rhs) -> lhs.getLastname().compareTo(rhs.getLastname());

    // Define predicate for the filter (java 7 :))
    Predicate<Person> startsWithC = new Predicate<Person>() {
      @Override
      public boolean test(Person person) {
        // TODO Auto-generated method stub
        return person.getLastname().startsWith("C");
      }
    };
    
    // Create list of people
    List<Person> people = Arrays.asList(
          new Person("Sean", "Fluffy", 14),
          new Person("Cole", "Pups", 8),
          new Person("Denise", "Acer", 40),
          new Person("Sue", "Corns", 27),
          new Person("Vincent", "Clappman", 32)
        );

    // Output all the records using the lambda expression
    System.out.println("All people");
    printAll.perform(people);
    
    // SORT
    // ----------------------------------------------------------------------------
    // Sort using the lambda expression
    Collections.sort(people, compIt); 
    
    // Show results
    System.out.println("Sorted by lastname");
    printAll.perform(people);
    
    // Another way to use lambda's, this one inline
    // Since sort takes a a comparator and that interface only has one method in it you can use
    // lambda's and pass the method implementation here, note the compiler knows the type of the
    // arguments to the comparator (they must be an element in list) so you don't need to 
    // define it in the argument list
    Collections.sort(people, (lhs, rhs) -> -(lhs.getLastname().compareTo(rhs.getLastname())));
         
    // Show results of descending sort
    System.out.println("Sorted by lastname (desc) using inline lambda");
    printAll.perform(people);
    
    // FILTER
    // ------------------------------------------------------------------------------
    // To do in Java7, condition is defined inline (annonymous inner class)
    System.out.println("Conditional printing");
    printConditionally(people, new Condition() { 
      @Override
      public boolean test(Person p) {
        return (p.getLastname().startsWith("C"));
      }      
    });
    
    // To do in Java7, condition is defined inline (annonymous inner class)
    System.out.println("Conditional2 printing");
    printConditionally2(people, startsWithC);
    
    // In Java 8
    // Since the predicate only has one method you can implement it with a lambda also
    System.out.println("Conditional2 printing with inline lambda");
    printConditionally2(people, p -> p.getLastname().startsWith("C"));
    
    
    // NOTE
    // -----------------------------------------------------------------------
    // Since printAll and printConditionally are almost the same you could call
    // printConditionally (or printConditionally2) all the time by just replacing
    // all the printAll's with printConditionally(people, p -> true);
    System.out.println("Showing how to use printcConditionally for all calls");
    printConditionally(people, p-> true);
       
  }
  
  private static void printConditionally2(List<Person> people, Predicate<Person> pred) {
    for (Person p: people) {
      if (pred.test(p)) System.out.println(p);
    }
  }
  
  // Iterate thru list and write out people that pass the condition
  private static void printConditionally(List<Person> people, Condition condition) {
    for (Person p: people) {
      if (condition.test(p)) System.out.println(p);
    }
  }

  @FunctionalInterface
  interface Condition {
    boolean test(Person p);
  }

  interface MyPersonInterface {
    void perform(List<Person> listOfPeople);
  }  
}
