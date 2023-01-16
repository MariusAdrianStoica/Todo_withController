package se.lexicon.dao;

import se.lexicon.model.Person;

import java.util.Optional;

public interface PersonDao extends BaseDao<Person>{ //add <T> to BaseDao in order to specify the type


    //Optional<Person> findByUsername(String username);

    //to find a person by username, first we have to be sure that that person is created
    // we use a help << Optional >> - which is a container, a helper class
    // - provides a check if person is null or !null
    // -> Optional<Person>

}
