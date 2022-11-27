package se.lexicon.dao;

import se.lexicon.model.AppUser;
import se.lexicon.model.Role;

import java.util.List;

public interface AppUserDao extends BaseDao<AppUser>{ // add <T> to BaseDao in order to specify the type

    //AppUser create(AppUser appUser);  -> moved to BaseDao

    //AppUser findById(Integer id);     -> moved to BaseDao

    //boolean deleteById(Integer id);   -> moved to BaseDao

    //void update(AppUser appUser);     -> moved to BaseDao

    //List<AppUser> findAll();          -> moved to BaseDao

    AppUser findByUsername(String username);

    List<AppUser> findByRole(Role role);
}
