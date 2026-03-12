import unittest
from Server.Dispatcher import Dispatcher
from Server.LoginHandler import LoginHandler


class MyTestCase(unittest.TestCase):
    def testValidDispatch(self):
        test_dispatcher = Dispatcher()
        handle = LoginHandler()
        test_dispatcher.add(handle)
        message = dict()
        message["reqtype"] = "LOGIN"
        response = test_dispatcher.dispatch(message)
        self.assertEqual("UNIMPLEMENTED", response["restype"])

    def testInputHasNoReqtype(self):
        test_dispatcher = Dispatcher()
        handle = LoginHandler()
        test_dispatcher.add(handle)
        message = dict()
        message["randomtype"] = "RANDOM"
        response = test_dispatcher.dispatch(message)
        self.assertEqual("BAD_INPUT", response["restype"])

    def testDispatcherHasHandler(self):
        test_dispatcher = Dispatcher()
        message = dict()
        message["reqtype"] = "PROVIDE_SNACKS"
        response = test_dispatcher.dispatch(message)
        self.assertEqual("MISSING_HANDLER", response["restype"])


if __name__ == '__main__':
    unittest.main()
