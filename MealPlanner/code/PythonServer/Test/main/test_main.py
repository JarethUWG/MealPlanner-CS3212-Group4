import time
import zmq
import json
import unittest
from threading import Thread
from Server.main import main

class TestMain(unittest.TestCase):

    def setUp(self):
        server_thread = Thread(target=main)
        server_thread.start()
        time.sleep(1)
        self.context = zmq.Context()
        self._socket = self.context.socket(zmq.REQ)
        self._socket.connect("tcp://127.0.0.1:5555")

    def tearDown(self):
        self._socket.close()
        self.context.term()

    def test_valid_request(self):
        message = dict()
        message["reqtype"] = "LOGIN"
        json_message = json.dumps(message)
        self._socket.send_string(json_message)
        json_response = self._socket.recv_string()
        response = json.loads(json_response)
        self.assertEqual("UNIMPLEMENTED", response.get("restype"))

    def test_bad_input(self):
        message = "Wrong type dummy"
        json_message = json.dumps(message)
        self._socket.send_string(json_message)
        json_response = self._socket.recv_string()
        response = json.loads(json_response)
        self.assertEqual("BAD_INPUT", response.get("restype"))


if __name__ == '__main__':
    unittest.main()
