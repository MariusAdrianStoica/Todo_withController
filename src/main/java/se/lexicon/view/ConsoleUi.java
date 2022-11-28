package se.lexicon.view;

import se.lexicon.model.AppUser;
import se.lexicon.model.Person;
import se.lexicon.model.Role;
import se.lexicon.model.TodoItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleUi {
    // we create this Console in order to get data from user input and display it
    //is responsible to take data from the user and display for user

    //display menu
    public MainMenuAction displayMenu(){
        System.out.println("1 - REGISTER");
        System.out.println("2 - CREATE TASK");
        System.out.println("3 - DISPLAY ALL TASKS");
        System.out.println("4 - EXIT" +"\n");

        System.out.println("Enter a number (1-4): ");
        //Scanner scanner = new Scanner(System.in); - declared in method getNumber()
        int operationCode =getNumber();   //int operationCode = scanner.nextInt(); -> reuse method to get number

        switch (operationCode){
            case 1:
                return MainMenuAction.REGISTER;
            case 2:
                return MainMenuAction.CREATE_TASK;
            case 3:
                return MainMenuAction.DISPLAY_TASK;
            default:
                return MainMenuAction.EXIT;
        }

    }

    //getString & getNumber from Console
    public int getNumber(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public String getString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // get person data (including AppUser)

    public Person getPersonData(){
        System.out.println("Username:");
        String username = getString();

        System.out.println("password:");
        String pwd = getString();

        AppUser appUserData = new AppUser(username, pwd, Role.ROLE_USER); //we create the appUser - role default

        System.out.println("firstName:");
        String firstName = getString();

        System.out.println("lastName:");
        String lastName = getString();

        Person personData = new Person(firstName, lastName, appUserData); //we create the person

        return personData;
    }

    //display person information
    public void displayPersonInformation(Person person){
        System.out.println(person.toString());
    }


    //get TodoItem data
    public TodoItem getTodoItemData(){
        System.out.println("Task name/Title: ");
        String title =getString();
        System.out.println("Description: ");
        String description = getString();
        System.out.println("Deadline (YYYY-MM-DD): ");
        String deadline= getString();

        System.out.println("Person Id:"); // person for assign the task
        Integer personId = getNumber();

        TodoItem todoItemData =new TodoItem(title, description, LocalDate.parse(deadline));

        Person personData=new Person();// in order to create an empty object person - we need a default constructor
           personData.setId(personId);
           // setting the id it means that we have the person(first, lastname, appUser) with that id

        todoItemData.setAssignee(personData); // setting the person to the task (TodoItem)
        return todoItemData;
    }

    //print TodoItem information
    public void displayTodoItemInformation(TodoItem todoItem){
        System.out.println(todoItem.toString());
    }

    //display all tasks
    public void displayAllTasks(List<TodoItem> todoItemList){
        for (TodoItem todoItem: todoItemList) {

            displayTodoItemInformation(todoItem); // same result as bellow
            //System.out.println(todoItem.toString());
        }
    }






}
