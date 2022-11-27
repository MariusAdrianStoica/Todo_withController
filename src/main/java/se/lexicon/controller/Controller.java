package se.lexicon.controller;

import se.lexicon.view.MainMenuAction;
import se.lexicon.dao.AppUserDao;
import se.lexicon.dao.PersonDao;
import se.lexicon.dao.impl.AppUserDaoImpl;
import se.lexicon.dao.impl.PersonDaoImpl;
import se.lexicon.model.AppUser;
import se.lexicon.model.Person;
import se.lexicon.view.ConsoleUi;

public class Controller {

    //is responsible to take data from Console and assign to Dao

    ConsoleUi ui; // declaring object as a field in order to reuse it in all methods
    PersonDao personDao; // declaring object as a field in order to reuse it in all methods
    AppUserDao appUserDao; // declaring object as a field in order to reuse it in all methods

    public Controller(){ // constructor
        ui= new ConsoleUi(); // instantiate object
        personDao=PersonDaoImpl.getInstance();
        appUserDao= AppUserDaoImpl.getInstance();
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
                    doDisplayTodoItem();
                    break;
                case EXIT:
                    System.exit(0); //finish application
            }
        }
    }
        public void doRegister() {
            Person personData = ui.getPersonData();
            //PersonDao personDao = PersonDaoImpl.getInstance(); //instantiate person
            AppUser appUserData = personData.getAppUser();// first we need the data for AppUser
            //before creating the person, we need also data for AppUser

            AppUser createdAppUser = appUserDao.create(appUserData); //we create an AppUUser in storage with data obtained from console

            personData.setAppUser(createdAppUser); //assign the appUser data to person
            Person createdPerson = personDao.create(personData); //we create a person in storage with data obtained from console

            ui.displayPersonInformation(createdPerson);


            System.out.println("doRegister method was executed!\n");
        }

        public void doCreateTodoItem() {
            System.out.println("doCreateTodoItem method was executed!\n");

        }
        public void doDisplayTodoItem() {
            System.out.println("doDisplayTodoItem method was executed!\n");

        }
    }
