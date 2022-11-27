package se.lexicon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {

    //fields
    private Integer id;
    private String firstName;
    private String lastName;
    private AppUser appUser;
    private List<TodoItem> assignedTodos;

    //Constructor - if you want to fetch data from the storage
    public Person(Integer id, String firstName, String lastName, List<TodoItem> assignedTodos) {
        setId(id);                          //this.id = id;
        setFirstName(firstName);            //this.firstName = firstName;
        setLastName(lastName);              //this.lastName = lastName;
        setAssignedTodos(assignedTodos);    //this.assignedTodos = assignedTodos;
        //without appUser - first it should register & we can assign appUser into setters method
    }

    // 2nd constructor if you want to register a person without task

    /**public Person(String firstName, String lastName) { */
        // but we have to force constructor to create a person only with an appUser

    public Person(String firstName, String lastName, AppUser appUser) {
        // id will be assigned only when we register person into storage
        setFirstName(firstName);
        setLastName(lastName);
        this.assignedTodos =new ArrayList<TodoItem>(); // empty list - no tasks
        setAppUser(appUser);
    }

    // Setters & Getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null) throw new RuntimeException( "id was null");
        // id should be generated in app. Not allowed to set id manually (not IllegalArgExc) - RuntimeExc
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) throw new IllegalArgumentException("firstName was null");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) throw new IllegalArgumentException("lastName was null");
        this.lastName = lastName;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("appUser was null");
        this.appUser = appUser;
    }

    public List<TodoItem> getAssignedTodos() {
        return assignedTodos;
    }

    public void setAssignedTodos(List<TodoItem> assignedTodos) {
        if (this.assignedTodos == null) assignedTodos=new ArrayList<TodoItem>(); //create empty list
        this.assignedTodos = assignedTodos;
    }

    // equals & hashcode - if you want to compare persons


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getLastName(), person.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName());
    }

    @Override
    public String toString() { // a description of persons
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
