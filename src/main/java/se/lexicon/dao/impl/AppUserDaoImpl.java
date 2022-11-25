package se.lexicon.dao.impl;

import se.lexicon.dao.AppUserDao;
import se.lexicon.dao.impl.sequencer.PersonIdSequencer;
import se.lexicon.model.AppUser;
import se.lexicon.model.Role;

import java.util.ArrayList;
import java.util.List;

public class AppUserDaoImpl implements AppUserDao {
    //implementing methods from DAO

    //define storage
    private List<AppUser> storage;

    private static AppUserDaoImpl instance; // ->step2

    //constructor
    private AppUserDaoImpl() { // public AppUserDaoImpl(){ ->step1
        storage = new ArrayList<>(); // create empty list
    }
    public static AppUserDaoImpl getInstance(){ // ->step 3
        if (instance == null) instance= new AppUserDaoImpl(); //instantiate
        return instance;
        }
        /*Singleton design pattern
        1. make constructor private ( appUserDaoImpl)
        2. define a static field (instance), before constructor, with the type of class name
        3.write a static method - classname -to check what value has instance
        */


    @Override
    public AppUser create(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("appUser was null");
        // check -> the username must not be duplicate
        appUser.setId(PersonIdSequencer.nextId());//generate id only when register appUser to storage
        storage.add(appUser);
        return appUser;
    }

    @Override
    public AppUser findById(Integer id) {
        if (id== null) throw new IllegalArgumentException("id was null");
        for (AppUser appUser : storage)
            if(appUser.getId().equals(id)) return appUser;
            // if we have single line in for, then  we can remove curly braces
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        AppUser result = findById(id);
        return storage.remove(result); //remove returns booleanö
    }

    @Override
    public void update(AppUser toUpdate) {
        if (toUpdate== null) throw new IllegalArgumentException("toUpdate was null");
        // find the appUser in storage using findById() method
        /*AppUser result = findById(toUpdate.getId());
        if (result!= null){*/

        for (AppUser original : storage){
            if (original.getId().equals(toUpdate.getId())){
                // if we find the element, we will start to set the new values in all fields
                //id is unique
                original.setUsername(toUpdate.getUsername());
                original.setPassword(toUpdate.getPassword());
                original.setRole(toUpdate.getRole());
                original.setActive(toUpdate.isActive());
                break;
            }
        }
    }

    @Override
    public List<AppUser> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public AppUser findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("username was null");
        for (AppUser appUser : storage)
            if(appUser.getUsername().equals(username)) return appUser;
        return null;
    }

    @Override
    public List<AppUser> findByRole(Role role) {
        if (role== null) throw new IllegalArgumentException("role was null");
        List<AppUser> filteredList =new ArrayList<>(); // generate empty list
        for (AppUser appUser : storage) {
            if (appUser.getRole()==role) filteredList.add(appUser);
        }
        // if we have single line in for, then  we can remove curly braces*/
        return filteredList;
    }
}
