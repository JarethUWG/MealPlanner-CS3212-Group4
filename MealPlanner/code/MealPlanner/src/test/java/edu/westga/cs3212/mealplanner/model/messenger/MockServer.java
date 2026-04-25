package edu.westga.cs3212.mealplanner.model.messenger;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

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
            Map<String, String> responseMap = new HashMap<String, String>();
            Map<String, Object> processedMessage;
            processedMessage = jsonConvert.toMap();
            if (processedMessage.get("reqtype") == null) {
                responseMap.put("restype", "no request");
            } else if (processedMessage.get("reqtype").equals("login")) {
                responseMap.put("restype", "login success");
            } else if (processedMessage.get("reqtype").equals("exit")) {
                responseMap.put("restype", "exit");
            } else {
                responseMap.put("restype", "no handle");
            }
            this.delay();
            jsonConvert = new JSONObject(responseMap);
            String socketReply = jsonConvert.toString();
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
