import unittest
from Server.Dispatcher import Dispatcher
from Server.Enums.Communication import Communication
from Server.Handlers.LoginHandler import LoginHandler
from Server.Enums.CommunicationType import CommunicationType


class TestDispatch(unittest.TestCase):
    def test_valid_dispatch(self):
        test_dispatcher = Dispatcher()
        handle = LoginHandler()
        test_dispatcher.add(handle)
        message = dict()
        message[Communication.REQUEST] = CommunicationType.LOGIN
        response = test_dispatcher.dispatch(message)
        self.assertEqual("BAD_INPUT", response[Communication.RESPONSE])

    def test_input_has_no_reqtype(self):
        test_dispatcher = Dispatcher()
        handle = LoginHandler()
        test_dispatcher.add(handle)
        message = dict()
        message["randomtype"] = "RANDOM"
        response = test_dispatcher.dispatch(message)
        self.assertEqual("BAD_INPUT", response[Communication.RESPONSE])

    def test_dispatcher_has_no_handler(self):
        test_dispatcher = Dispatcher()
        message = dict()
        message[Communication.REQUEST] = "PROVIDE_SNACKS"
        response = test_dispatcher.dispatch(message)
        self.assertEqual("MISSING_HANDLER", response[Communication.RESPONSE])


if __name__ == '__main__':
    unittest.main()
