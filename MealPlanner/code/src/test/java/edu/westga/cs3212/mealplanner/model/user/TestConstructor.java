package edu.westga.cs3212.mealplanner.model.user;

import edu.westga.cs3212.mealplanner.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestConstructor {

    @Test
    void testNullName() {
        assertThrows(IllegalArgumentException.class, () -> new User(null, "testUsername", "testPassword"));
    }

    @Test
    void testEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new User("", "testUsername", "testPassword"));
    }

    @Test
    void testBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new User(" ", "testUsername", "testPassword"));
    }

    @Test
    void testNullUsername() {
        assertThrows(IllegalArgumentException.class, () -> new User("testName", null, "testPassword"));
    }

    @Test
    void testEmptyUsername() {
        assertThrows(IllegalArgumentException.class, () -> new User("testName", "", "testPassword"));
    }

    @Test
    void testBlankUsername() {
        assertThrows(IllegalArgumentException.class, () -> new User("testName", " ", "testPassword"));
    }

    @Test
    void testNullPassword() {
        assertThrows(IllegalArgumentException.class, () -> new User("testName", "testUsername", null));
    }

    @Test
    void testEmptyPassword() {
        assertThrows(IllegalArgumentException.class, () -> new User("testName", "testUsername", ""));
    }

    @Test
    void testBlankPassword() {
        assertThrows(IllegalArgumentException.class, () -> new User("testName", "testUsername", " "));
    }

    @Test
    void testValidConstructorCall() {
        User testUser = new User("testName", "testUsername", "testPassword");
        assertEquals("testName", testUser.getName(), "checking the name of the user");
        assertEquals("testUsername", testUser.getUsername(), "checking the username of the user");
        assertEquals("testPassword", testUser.getPassword(), "checking the password of the user");
    }
}
