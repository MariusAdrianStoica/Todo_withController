package se.lexicon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {

    //fields
    private Integer person_id;
    private String first_name;
    private String last_name;

    public Person(Integer person_id, String first_name, String last_name) {
        setId(person_id);
        setFirstName(first_name);
        setLastName(last_name);
    }

    // 2nd constructor if you want to register a person without task

    /**public Person(String firstName, String lastName) { */
        // but we have to force constructor to create a person only with an appUser

    public Person(String first_name, String last_name) {
        // id will be assigned only when we register person into storage
        setFirstName(first_name);
        setLastName(last_name);
    }

    public Person(){ //default constructor - in order to implement getTodoItemData()


    }

    // Setters & Getters
    public Integer getId() {
        return person_id;
    }

    public void setId(Integer id) {
        if (id == null) throw new RuntimeException( "id was null");
        this.person_id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        if (first_name == null) throw new IllegalArgumentException("firstName was null");
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        if (last_name == null) throw new IllegalArgumentException("lastName was null");
        this.last_name = last_name;
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
                "id=" + person_id +
                ", firstName='" + first_name + '\'' +
                ", lastName='" + last_name + '\'' +
                '}';
    }
}
