package se.lexicon.model;

import java.util.Objects;

public class AppUser {
    //fields
    private Integer id;
    private String username;
    private String password;
    private boolean active;
    private Role role;
    //private LocalDate createDate;

    //Constructor
    public AppUser(Integer id, String username, String password, boolean active, Role role) {
        setId(id);                      //this.id = id;
        setUsername(username);          //this.username = username;
        setPassword(password);          //this.password = password;
        setActive(active);              //this.active = active;
        setRole(role);                  //this.role = role;
    }

    //if you want to create an appUser with default role
    public AppUser(String username, String password, Role role) {
        setUsername(username);          //this.username = username;
        setPassword(password);          //this.password = password;
        this.active =true;
        setRole(role);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if( id == null) throw new RuntimeException("id was null");
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null) throw new IllegalArgumentException("username was null");
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null) throw new IllegalArgumentException("password was null");
        if (password.length()<8) throw new IllegalArgumentException("password length was not valid");
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        if (role == null) role= Role.ROLE_USER; //default ROLE_USER
        this.role = role;
    }

    //equals & hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o== null || getClass()!=o.getClass()) return false; //!(o instanceof AppUser)
        AppUser appUser = (AppUser) o;
        return isActive() == appUser.isActive() && Objects.equals(getId(), appUser.getId()) && Objects.equals(getUsername(), appUser.getUsername()) && getRole() == appUser.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), isActive(), getRole());
    }

    //toString
    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", active=" + active +
                '}';
    }
}

