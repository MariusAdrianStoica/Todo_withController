package se.lexicon.dao;

import se.lexicon.model.AppUser;

import java.util.List;

/**
 * Generic programming is a style of computer programming
 * we can code without specifying datatype(s)
 * E: Element
 * T: Type
 * K: Key
 * N: Number
 * V: Value

 */
public interface BaseDao<T> {
    // an interface to group all the methods that exists in AppUserDao, PersonDao, TodoItemDao
    //here we have a problem - we can not override appUser methods in person class
    //we have to use a generic type
    // in interface name we need to add " <T> " after BaseDao

    T create(T model);   //AppUser create(AppUser appUser); -> instead of AppUser, we use T -Type

    T findById(Integer id); //AppUser findById(Integer id);

    boolean deleteById(Integer id);

    void update(T model); //void update(AppUser appUser);

    List<T> findAll(); // List<AppUser> findAll();


}
