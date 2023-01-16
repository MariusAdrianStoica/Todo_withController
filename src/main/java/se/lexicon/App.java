package se.lexicon;

import se.lexicon.controller.Controller;

public class App
{
    public static void main( String[] args )
    {

        System.out.println("ToDoIt - JDBC ");
        System.out.println("*******************************");



       /*
        //AppUser
        //get AppUser data -> info from console
        AppUser appUserData1 = new AppUser("test", "password", Role.ROLE_USER);
        AppUser appUserData2 = new AppUser("user2", "password", Role.ROLE_USER);
        AppUser appUserData3 = new AppUser("user3", "password", Role.ROLE_USER);

        // first instantiate AppUserDao to have access to storage
        AppUserDao appUserDao = AppUserDaoImpl.getInstance(); // new AppUserDaoImpl();
        // Singleton method - we are not allow to instantiate a private class
        // getInstance() is responsible to instantiate
        AppUser createdAppUser1 = appUserDao.create(appUserData1); //register user into storage
        AppUser createdAppUser2 = appUserDao.create(appUserData2); //register user into storage
        AppUser createdAppUser3 = appUserDao.create(appUserData3); //register user into storage

        System.out.println( "---------------");
        System.out.println(appUserDao.findAll());
        System.out.println( "---------------");

        AppUser updateAppUser101 =new AppUser(101, "appUser", "password", false, Role.ROLE_ADMIN);
        appUserDao.update(updateAppUser101);
        System.out.println(appUserDao.findById(101));
        System.out.println( "---------------");

        AppUserDao appUserDao2 = AppUserDaoImpl.getInstance(); //Singleton pattern - try to instantiate again
        //if we instantiate again, it will begin with creating an empty storage -> the result is that we delete all data
        System.out.println(appUserDao2.findAll());


        //Person data

        Person personData1 = new Person("Test", "Test", appUserData1);
        //personData1.setAppUser(createdAppUser1); // we force constructor in person to take appUser

        PersonDao personDao =PersonDaoImpl.getInstance(); //PersonDao personDao =new PersonDaoImpl();
        Person createdPerson1 = personDao.create(personData1);
        System.out.println(createdPerson1.getAppUser());

        System.out.println(personDao.findAll());
        System.out.println( "---------------");
        Optional<Person> optionalPerson = personDao.findByUsername("user");

        //result of findByUsername is an Optional<Person> object
        if (optionalPerson.isPresent()) System.out.println(optionalPerson.get());
        else System.out.println("Person not found");
        // .isPresent() is not null, if person exists


        //TodoItem data
        System.out.println( "---------------");

        TodoItem task1 = new TodoItem("task1", "test description", LocalDate.parse("2022-12-10"));
        task1.setAssignee(createdPerson1); //assign the task to the person

        TodoItemDao todoItemDao = TodoItemDaoImpl.getInstance();
        TodoItem createdTask1= todoItemDao.create(task1);

        System.out.println( todoItemDao.findAll()); */


        //we want to use Controller in order to get data
        Controller controller = new Controller();
        controller.doMainMenu();


        // Main purpose:  MVC -pattern (model-view-controller)
        //Model     : we categorize all the data
        //dao       : a pattern to make separate layers for data for manipulating data
        //View      : take data from console and display data
        //Controller: communicate with view and update model through dao






    }
}
