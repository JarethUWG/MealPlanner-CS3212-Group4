package edu.westga.cs3212.mealplanner.model;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;
import java.util.Map;
import org.json.JSONObject;

/**
 * The main point of communication
 * between the client and server.
 *
 * @author Jareth Batty
 * @version Spring 2026
 */
public class Messenger {

    /**
     * Takes a request in the form of a map and returns
     * a response map.
     * @param message The request map to be sent to the server
     * @return The response from the server
     */
    public static Map<String, Object> request(Map<String, Object> message) {
        Context context = ZMQ.context(1);
        Socket socket = context.socket(ZMQ.REQ);
        socket.connect("tcp://127.0.0.1:5555");
        JSONObject jsonConvert = new JSONObject(message);
        String socketRequest = jsonConvert.toString();
        socket.send(socketRequest.getBytes(ZMQ.CHARSET), 0);

        byte[] reply = socket.recv(0);
        String rawResponse = new String(reply, ZMQ.CHARSET);
        jsonConvert = new JSONObject(rawResponse);
        Map<String, Object> processedResponse = jsonConvert.toMap();
        socket.close();
        context.term();
        return processedResponse;
    }
}
