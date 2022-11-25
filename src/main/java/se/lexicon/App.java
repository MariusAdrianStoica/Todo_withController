package se.lexicon;

import se.lexicon.dao.AppUserDao;
import se.lexicon.dao.impl.AppUserDaoImpl;
import se.lexicon.model.AppUser;
import se.lexicon.model.Role;

public class App
{
    public static void main( String[] args )
    {
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



    }
}
