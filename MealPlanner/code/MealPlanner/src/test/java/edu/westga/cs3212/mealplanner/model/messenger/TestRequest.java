package edu.westga.cs3212.mealplanner.model.messenger;

import edu.westga.cs3212.mealplanner.model.Messenger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.zeromq.ZMQ;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestRequest {
    private static ZMQ.Context context;
    private static ZMQ.Socket socket;
    private static Thread thread;

    @BeforeAll
    static void setUp() {
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
    void testNullInput() {
        this.delay();
        Map<String, Object> message = null;
        Map<String, Object> response = Messenger.request(message);
        assertEquals("no request", response.get("restype"));
    }

    @Test
    void testEmptyMap() {
        this.delay();
        Map<String, Object> message = new HashMap<String, Object>();
        Map<String, Object> response = Messenger.request(message);
        assertEquals("no request", response.get("restype"));
    }

    @Test
    void testNoReqtype() {
        this.delay();
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("random", "non-sense");
        Map<String, Object> response = Messenger.request(message);
        assertEquals("no request", response.get("restype"));
    }

    @Test
    void testReqtypeNotHandled() {
        this.delay();
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("reqtype", "non-sense");
        Map<String, Object> response = Messenger.request(message);
        assertEquals("no handle", response.get("restype"));
    }

    @Test
    void testValidReqtype() {
        this.delay();
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("reqtype", "login");
        Map<String, Object> response = Messenger.request(message);
        assertEquals("login success", response.get("restype"));
    }

    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
