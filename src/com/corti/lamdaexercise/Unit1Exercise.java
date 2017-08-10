package com.corti.lamdaexercise;

import java.util.Arrays;
import java.util.List;

public class Unit1Exercise {

  public static void main(String[] args) {
    // Create list of people
    List<Person> people = Arrays.asList(
          new Person("Sean", "Fluffy", 14),
          new Person("Cole", "Pups", 8),
          new Person("Denise", "Acer", 40),
          new Person("Sue", "Corns", 27),
          new Person("Vincent", "Clappman", 32)
        );

    // Exercise one print all members of list
    MyPersonInterface printAll = list -> { 
      for (Person aPerson: people) { 
        System.out.println(aPerson.toString()); 
      }
    };
    printAll.perform(people);
  }

  interface MyPersonInterface {
    void perform(List<Person> list);
  }
}
