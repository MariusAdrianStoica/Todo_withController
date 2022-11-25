package se.lexicon.model;

//import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*; //we don't need to write class name Assertions.
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class AppUserTest {

    private AppUser testObject;
    @BeforeEach
    public void setup(){
        testObject = new AppUser(1, "user", "password", true, Role.ROLE_USER);
    }
    @Test
    public void testObject_successfully_instantiated(){
    //Assertions.assertEquals(1, testObject.getId());
    assertEquals(1, testObject.getId());
    assertEquals("user", testObject.getUsername());
    assertNotEquals("USER", testObject.getUsername());
    assertEquals("password", testObject.getPassword());
    assertTrue(testObject.isActive());
    assertEquals(Role.ROLE_USER, testObject.getRole());
    // we can remove word Assertions by << import static org.junit.jupiter.api.Assertions.* >>
    }
    @Test
    public void test_throws_IllegalArgumentException_on_null_username(){
    assertThrows(IllegalArgumentException.class, () ->new AppUser(null, "password", Role.ROLE_USER));

            //
    // instead of new Executable(), we can write " () -> "
    /*assertThrows(IllegalArgumentException.class, new Executable() {
        @Override
        public void execute() throws Throwable {
            AppUser test = new AppUser(null, "password", Role.ROLE_USER);
        }
    });*/
    }
    @Test
    public void setPassword_null_throws_IllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () ->testObject.setPassword(null));
    }

    @Test
    public void setPassword_length_5_throws_IllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () ->testObject.setPassword("12345"));
    }

    @Test
    public void testObject_equal_true(){
        AppUser expected = new AppUser(1, "user", "password", true, Role.ROLE_USER);
        assertEquals(expected, testObject);
    }

    @Test
    public void testObject_equal_false(){
        AppUser expected = new AppUser(1, "USER", "123456789", true, Role.ROLE_ADMIN);
        assertNotEquals(expected, testObject);
    }

    //test_hashcode
    @Test
    public void test_hashCode(){
        AppUser expected = new AppUser(1, "user", "password", true, Role.ROLE_USER);
        assertEquals(expected.hashCode(), testObject.hashCode());
    }

}
