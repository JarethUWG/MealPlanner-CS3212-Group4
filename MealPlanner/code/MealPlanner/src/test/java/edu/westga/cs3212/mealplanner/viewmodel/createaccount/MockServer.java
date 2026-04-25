package edu.westga.cs3212.mealplanner.viewmodel.createaccount;

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

    @Override
    public void run() {
        Context context = ZMQ.context(1);
        Socket socket = context.socket(ZMQ.REP);
        socket.bind("tcp://127.0.0.1:5555");

        while (!Thread.currentThread().isInterrupted()) {
            byte[] reply = socket.recv(0);
            String message = new String(reply, ZMQ.CHARSET);
            JSONObject jsonConvert = new JSONObject(message);
            Map<String, Object> responseMap = new HashMap<String, Object>();
            Map<String, Object> processedMessage;
            processedMessage = jsonConvert.toMap();
            String username = (String) processedMessage.get("username");
            String password = (String) processedMessage.get("password");
            if (processedMessage.get("reqtype").equals("exit")) {
                responseMap.put("restype", "exit");
            } else if (!username.isBlank() && password.length() >= 5 && processedMessage.get("reqtype").equals("CREATE ACCOUNT")
            && !username.equals("Username")) {
                responseMap.put("restype", "VALID");
            } else {
                responseMap.put("restype", "INVALID");
            }
            jsonConvert = new JSONObject(responseMap);
            String socketReply = jsonConvert.toString();
            this.delay();
            socket.send(socketReply.getBytes(ZMQ.CHARSET), 0);
            if (responseMap.get("restype").equals("exit")) {
                socket.close();
                context.term();
                return;
            }
        }

        socket.close();
        context.term();

    }

    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
