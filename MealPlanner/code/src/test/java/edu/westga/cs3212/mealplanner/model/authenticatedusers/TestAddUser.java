package edu.westga.cs3212.mealplanner.model.authenticatedusers;

import edu.westga.cs3212.mealplanner.model.AuthenticatedUsers;
import edu.westga.cs3212.mealplanner.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestAddUser {

    @Test
    void testNullUser() {
        AuthenticatedUsers users = new AuthenticatedUsers();
        assertThrows(IllegalArgumentException.class, () -> users.addUser(null));
    }

    @Test
    void testUserAlreadyInList() {
        AuthenticatedUsers users = new AuthenticatedUsers();
        User user = new User("name", "username", "password");
        users.addUser(user);
        assertThrows(IllegalArgumentException.class, () -> users.addUser(user));
    }

    @Test
    void testOneValidUserAdded() {
        AuthenticatedUsers users = new AuthenticatedUsers();
        User user = new User("name", "username", "password");
        users.addUser(user);
        assertEquals("name", users.getUsers().get(1).getName(), "Ensuring name");
        assertEquals("username", users.getUsers().get(1).getUsername(), "Ensuring username");
        assertEquals("password", users.getUsers().get(1).getPassword(), "Ensuring password");
    }

    @Test
    void testMultipleValidUsersAdded() {
        AuthenticatedUsers users = new AuthenticatedUsers();
        User user1 = new User("name1", "username1", "password1");
        User user2 = new User("name2", "username2", "password2");
        User user3 = new User("name3", "username3", "password3");
        users.addUser(user1);
        users.addUser(user2);
        users.addUser(user3);
        assertEquals("name1", users.getUsers().get(1).getName(), "Ensuring name of user1");
        assertEquals("username1", users.getUsers().get(1).getUsername(), "Ensuring username of user1");
        assertEquals("password1", users.getUsers().get(1).getPassword(), "Ensuring password of user1");
        assertEquals("name2", users.getUsers().get(2).getName(), "Ensuring name of user2");
        assertEquals("username2", users.getUsers().get(2).getUsername(), "Ensuring username of user2");
        assertEquals("password2", users.getUsers().get(2).getPassword(), "Ensuring password of user2");
        assertEquals("name3", users.getUsers().get(3).getName(), "Ensuring name of user3");
        assertEquals("username3", users.getUsers().get(3).getUsername(), "Ensuring username of user3");
        assertEquals("password3", users.getUsers().get(3).getPassword(), "Ensuring password of user3");
    }

}
