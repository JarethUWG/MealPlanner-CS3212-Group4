import unittest
from Server.Dispatcher import Dispatcher
from Server.LoginHandler import LoginHandler


class TestDispatch(unittest.TestCase):
    def test_valid_dispatch(self):
        test_dispatcher = Dispatcher()
        handle = LoginHandler()
        test_dispatcher.add(handle)
        message = dict()
        message["reqtype"] = "LOGIN"
        response = test_dispatcher.dispatch(message)
        self.assertEqual("UNIMPLEMENTED", response["restype"])

    def test_input_has_no_reqtype(self):
        test_dispatcher = Dispatcher()
        handle = LoginHandler()
        test_dispatcher.add(handle)
        message = dict()
        message["randomtype"] = "RANDOM"
        response = test_dispatcher.dispatch(message)
        self.assertEqual("BAD_INPUT", response["restype"])

    def test_dispatcher_has_no_handler(self):
        test_dispatcher = Dispatcher()
        message = dict()
        message["reqtype"] = "PROVIDE_SNACKS"
        response = test_dispatcher.dispatch(message)
        self.assertEqual("MISSING_HANDLER", response["restype"])


if __name__ == '__main__':
    unittest.main()
