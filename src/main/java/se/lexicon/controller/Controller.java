package se.lexicon.controller;

import se.lexicon.MySQLConnection;
import se.lexicon.dao.TodoItemDao;
import se.lexicon.dao.impl.TodoItemDaoImpl;
import se.lexicon.exception.DBConnectionException;
import se.lexicon.model.TodoItem;
import se.lexicon.view.MainMenuAction;
import se.lexicon.dao.AppUserDao;
import se.lexicon.dao.PersonDao;
import se.lexicon.dao.impl.AppUserDaoImpl;
import se.lexicon.dao.impl.PersonDaoImpl;
import se.lexicon.model.AppUser;
import se.lexicon.model.Person;
import se.lexicon.view.ConsoleUi;

import java.sql.*;
import java.util.List;

public class Controller {

    //is responsible to take data from Console and assign to Dao

    ConsoleUi ui; // declaring object as a field in order to reuse it in all methods
    PersonDao personDao; // declaring object as a field in order to reuse it in all methods
    AppUserDao appUserDao; // declaring object as a field in order to reuse it in all methods

    TodoItemDao todoItemDao; // declaring object as a field in order to reuse it in all methods

    public Controller(){ // constructor
        ui= new ConsoleUi(); // instantiate object
        personDao=PersonDaoImpl.getInstance();
        //appUserDao= AppUserDaoImpl.getInstance();
        todoItemDao= TodoItemDaoImpl.getInstance();
    }

    //we want to reuse all functionalities from Dao and ConsoleUi
    //main logic inside the Controller

    public void doMainMenu() {

        //ConsoleUi ui = new ConsoleUi(); we want to reuse object in all methods
        while (true) {//infinitive loop
            MainMenuAction action = ui.displayMenu();

            switch (action) {
                case REGISTER:
                    doRegister();
                    break;
                case CREATE_TASK:
                    doCreateTodoItem();
                    break;
                case DISPLAY_TASK:
                    doDisplayTodoItems();
                    break;
                case EXIT:
                    System.exit(0); //finish application
            }
        }
    }
        public void doRegister() {
            Person personData = ui.getPersonData();
            //PersonDao personDao = PersonDaoImpl.getInstance(); //instantiate person
            // ->AppUser appUserData = personData.getAppUser();// first we need the data for AppUser
            //before creating the person, we need also data for AppUser

            // ->AppUser createdAppUser = appUserDao.create(appUserData); //we create an AppUUser in storage with data obtained from console

            // ->personData.setAppUser(createdAppUser); //assign the appUser data to person
            Person createdPerson = personDao.create(personData); //we create a person in storage with data obtained from console

            ui.displayPersonInformation(createdPerson);
            String query = "INSERT INTO PERSON(person_id, first_name, last_name) VALUES (?,?,?)";

            try (
                    Connection connection = MySQLConnection.getConnection();
                    //PreparedStatement preparedStatement = connection.prepareStatement(query);

                    //in order to get the id of the new row: Statement.RETURN_GENERATED_KEYS
                    PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ){


                //preparedStatement.setString(1, "TEST1");
                preparedStatement.setString(1, String.valueOf(createdPerson.getId()));
                preparedStatement.setString(2, createdPerson.getFirstName());
                preparedStatement.setString(3, createdPerson.getLastName());

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + "rows affected");

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



            System.out.println("doRegister method was executed!\n");
        }



        public void doCreateTodoItem() {
        TodoItem todoItemData = ui.getTodoItemData();
        Person personData = todoItemData.getAssignee();

        Person foundPerson = personDao.findById(personData.getId());

        todoItemData.setAssignee(foundPerson); // assign person to the task
        TodoItem createdTodoItem = todoItemDao.create(todoItemData);

        String queryToDoItem = "INSERT INTO TODO_ITEM(todo_id, title, description, deadline, done, assignee_id) VALUES (?,?,?,?,?,?)";

        ui.displayTodoItemInformation(createdTodoItem);

            try (
                    Connection connection = MySQLConnection.getConnection();
                    //PreparedStatement preparedStatement = connection.prepareStatement(query);

                    //in order to get the id of the new row: Statement.RETURN_GENERATED_KEYS
                    PreparedStatement preparedStatement = connection.prepareStatement(queryToDoItem, Statement.RETURN_GENERATED_KEYS);
            ){

                preparedStatement.setInt(1, createdTodoItem.getId());
                preparedStatement.setString(2, createdTodoItem.getTitle());
                preparedStatement.setString(3, createdTodoItem.getDescription());
                preparedStatement.setDate(4, Date.valueOf(createdTodoItem.getDeadline()));
                preparedStatement.setBoolean(5, createdTodoItem.isDone());
                preparedStatement.setInt(6, createdTodoItem.getAssignee().getId());

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + "rows affected");

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





        System.out.println("doCreateTodoItem method was executed!\n");
        }
        public void doDisplayTodoItems() {
            List<TodoItem> todoItemList = todoItemDao.findAll();
            ui.displayAllTasks(todoItemList);

            System.out.println("doDisplayTodoItem method was executed!\n");

        }
    }
