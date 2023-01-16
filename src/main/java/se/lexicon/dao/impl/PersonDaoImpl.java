package se.lexicon.dao.impl;

import se.lexicon.dao.PersonDao;
import se.lexicon.dao.impl.sequencer.PersonIdSequencer;
import se.lexicon.model.AppUser;
import se.lexicon.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDaoImpl implements PersonDao {

    private List<Person> storage;
    private static PersonDaoImpl instance; /**-> step2 Singleton design */

    /**Singleton design pattern:
        step1. make constructor private
        step2. define a private static field named (instance) with the class type (PersonDaoImpl)
        step3. define a static method-getInstance() (class type) with if condition to check if the object is null or not

     */
    private PersonDaoImpl(){ /**-> step1 Singleton design*/ // public PersonDaoImpl(){
        storage = new ArrayList<>(); //empty list in the constructor
    }
    public static PersonDaoImpl getInstance(){ /**-> step3 Singleton design*/
        if (instance == null) instance = new PersonDaoImpl(); //instantiate
        return instance;
    }

    @Override
    public Person create(Person model) {
        if (model == null) throw new IllegalArgumentException("person was null");
            // add a new id to Person

        model.setId(PersonIdSequencer.nextId()); //generate id only when register new Person to storage
        storage.add(model);
        return model;
    }

    @Override
    public Person findById(Integer id) {
        if (id== null) throw new IllegalArgumentException("id was null");
        for (Person person : storage)
            if(person.getId().equals(id)) return person;
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        //Person result = findById(id);
        return storage.remove(findById(id));
    }

    @Override
    public void update(Person model) {
        // todo: implement later

    }

    @Override
    public List<Person> findAll() {
        return  new ArrayList<>(storage);
    }

    /*@Override
    public Optional<Person> findByUsername(String username) { //add Optional<Person>
        if (username== null) throw new IllegalArgumentException("username was null");
        for (Person person : storage)
            if(person.getAppUser().getUsername().equals(username)) return Optional.of(person);
        return Optional.empty();
    }*/
}
