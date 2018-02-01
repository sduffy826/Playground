package com.corti.lamdaexercise;

public class Person {
  private String lastname;
  private String firstname;
  private int age;
  
  public Person(String firstname, String lastname, int age) {
    super();
    this.lastname = lastname;
    this.firstname = firstname;
    this.age = age;
  }

  public String getLastname() {
    return lastname;
  }

  public String getFirstname() {
    return firstname;
  }

  public int getAge() {
    return age;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "People [lastname=" + lastname + ", firstname=" + firstname
        + ", age=" + age + "]";
  }
}
