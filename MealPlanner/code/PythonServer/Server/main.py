import time

import zmq
import json
from Server.Dispatcher import Dispatcher
from Server.GetMealHandler import GetMealHandler
from Server.LoginHandler import LoginHandler

"""
Entry point for the python server.
"""
def main():
    message_dispatcher = Dispatcher()
    login_handler = LoginHandler()
    message_dispatcher.add(login_handler)
    message_dispatcher.add(GetMealHandler())
    context = zmq.Context()
    socket = context.socket(zmq.REP)
    socket.bind("tcp://127.0.0.1:5555")
    while True:
        json_input = socket.recv_string()
        message = json.loads(json_input)
        time.sleep(1)
        if isinstance(message, dict):
            response = message_dispatcher.dispatch(message)
            json_response = json.dumps(response)
            socket.send_string(json_response)
        else:
            response = dict()
            response["restype"] = "BAD_INPUT"
            json_response = json.dumps(response)
            socket.send_string(json_response)

if (__name__ == "__main__"):
    main()