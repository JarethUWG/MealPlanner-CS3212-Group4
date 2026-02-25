package edu.westga.cs3212.mealplanner.model.authenticatedusers;

import edu.westga.cs3212.mealplanner.model.AuthenticatedUsers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestConstructor {

    @Test
    void testValidConstructorCall() {
        AuthenticatedUsers users = new AuthenticatedUsers();
        assertEquals("Username", users.getUsers().getFirst().getUsername(), "Ensuring correct username");
        assertEquals("Password", users.getUsers().getFirst().getPassword(), "Ensuring correct password");
    }

}
