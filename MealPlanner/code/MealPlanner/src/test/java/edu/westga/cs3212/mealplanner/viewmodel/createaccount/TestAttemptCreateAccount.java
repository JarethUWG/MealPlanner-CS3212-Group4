package edu.westga.cs3212.mealplanner.viewmodel.createaccount;

import edu.westga.cs3212.mealplanner.model.Messenger;
import edu.westga.cs3212.mealplanner.model.SystemInfo;
import edu.westga.cs3212.mealplanner.viewmodel.CreateAccountViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zeromq.ZMQ;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TestAttemptCreateAccount {
    private static ZMQ.Context context;
    private static ZMQ.Socket socket;
    private static Thread thread;

    @BeforeEach
    void setUp() {
        SystemInfo.setLoggedInUser(null);
        SystemInfo.setId(0);
    }

    @BeforeAll
    static void setUpAll() {
        MockServer mockServer = new MockServer();
        thread = new Thread(mockServer);
        thread.start();
        context = ZMQ.context(1);
        socket = context.socket(ZMQ.REQ);
        socket.connect("tcp://127.0.0.1:5555");
    }

    @AfterAll
    static void tearDown() {
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("reqtype", "exit");
        Messenger.request(message);
        socket.close();
        context.term();
    }

    @Test
    void testNoUsername() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("");
        passwordProperty.set("Test");

        assertThrows(IllegalArgumentException.class, testViewModel::attemptCreateAccount);
    }

    @Test
    void testNoPassword() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("Test");
        passwordProperty.set("");

        assertThrows(IllegalArgumentException.class, testViewModel::attemptCreateAccount);
    }

    @Test
    void testCredentialsDontMatch() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("Test Name");
        passwordProperty.set("Test Password");

        assertTrue(testViewModel.attemptCreateAccount());
    }

    @Test
    void testCredentialsMatch() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("Username");
        passwordProperty.set("Password");

        assertFalse(testViewModel.attemptCreateAccount());
    }
}
