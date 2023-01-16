package se.lexicon.dao.impl;

import se.lexicon.MySQLConnection;
import se.lexicon.dao.PeopleDao;
import se.lexicon.dao.impl.sequencer.PersonIdSequencer;
import se.lexicon.exception.DBConnectionException;
import se.lexicon.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PeopleDaoImpl implements PeopleDao {

    private List<Person> storage;

    private static PeopleDaoImpl instance;

    /**Singleton design pattern:
     step1. make constructor private
     step2. define a private static field named (instance) with the class type (PersonDaoImpl)
     step3. define a static method-getInstance() (class type) with if condition to check if the object is null or not

     */
    private PeopleDaoImpl(){ /**-> step1 Singleton design*/ // public PersonDaoImpl(){
        storage = new ArrayList<>(); //empty list in the constructor
    }
    public static PeopleDaoImpl getInstance(){ /**-> step3 Singleton design*/
        if (instance == null) instance = new PeopleDaoImpl(); //instantiate
        return instance;
    }
    @Override
    public Person create(Person model) {

        if (model == null) throw new IllegalArgumentException("person was null");
        model.setId(PersonIdSequencer.nextId());
        storage.add(model);

        String query = "INSERT INTO PERSON(person_id, first_name, last_name) VALUES (?,?,?)";

        try (
                Connection connection = MySQLConnection.getConnection();
                //PreparedStatement preparedStatement = connection.prepareStatement(query);

                //in order to get the id of the new row: Statement.RETURN_GENERATED_KEYS
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ){
            preparedStatement.setString(1, String.valueOf(PersonIdSequencer.nextId()));
            preparedStatement.setString(2, model.getFirstName());
            preparedStatement.setString(3, model.getLastName());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected);

            // to get the generated key (id of the new row
            try(
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                //if we add ResultSet to try(resources), it will close automatically

                if (resultSet.next()) { // if the row exists
                    System.out.println("Person ID is:" + resultSet.getInt(1));
                }
            }
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    return model;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Person findById(Integer id) {
        return null;
    }

    /*@Override
    public List findByName(String name) {
        return null;
    }*/


    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public void update(Person model) {

    }

   /* @Override
    public Optional<Person> findByUsername(String username) {
        return Optional.empty();
    }*/
}
