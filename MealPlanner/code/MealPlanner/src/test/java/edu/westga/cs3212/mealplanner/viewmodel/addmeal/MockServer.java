package edu.westga.cs3212.mealplanner.viewmodel.addmeal;

import org.json.JSONObject;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import java.util.HashMap;
import java.util.Map;

/**
 * Mock server utilized for testing purposes
 */
public class MockServer implements Runnable {
    private byte[] currentReply;

    public byte[] getCurrentReply() {
        return this.currentReply;
    }

    @Override
    public void run() {
        Context context = ZMQ.context(1);
        Socket socket = context.socket(ZMQ.REP);
        socket.bind("tcp://127.0.0.1:5555");
        while (!Thread.currentThread().isInterrupted()) {
            byte[] reply = socket.recv(0);
            this.currentReply = reply;
            socket.send(reply);
        }
    }
}
