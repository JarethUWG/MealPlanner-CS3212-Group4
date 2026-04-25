package edu.westga.cs3212.mealplanner.model;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import java.util.HashMap;
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
        JSONObject convertedMessage = new JSONObject(message);
        String socketRequest = convertedMessage.toString();
        socket.send(socketRequest.getBytes(ZMQ.CHARSET), 0);

        byte[] reply = socket.recv(0);
        String rawResponse = new String(reply, ZMQ.CHARSET);
        var convertedResponse = new JSONObject(rawResponse);
        Map<String, Object> processedResponse = convertedResponse.toMap();
        socket.close();
        context.term();
        return processedResponse;
    }

    /**
     * Requests planner information from the server and returns a deserialized Planner if successful, otherwise returns null.
     *
     * @return Planner or null
     */
    public static Planner requestPlanner() {
        var plannerRequest = new HashMap<String, Object>();
        plannerRequest.put("reqtype", "GET PLANNER");
        plannerRequest.put("id", SystemInfo.getId());
        var receivedInfo = Messenger.request(plannerRequest);
        Planner planner = null;

        if (receivedInfo.get("restype").equals("VALID")) {
            var plannerInfo = (Map<String, Object>) receivedInfo.get("planner");
            planner = Planner.deserialize(plannerInfo);
        }

        return planner;
    }
}
