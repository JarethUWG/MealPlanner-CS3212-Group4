import unittest
from Server.Dispatcher import Dispatcher
from Server.Handlers.LoginHandler import LoginHandler
from Server.Enums.CommunicationType import CommunicationType


class TestAdd(unittest.TestCase):
    def test_add(self):
        test_dispatcher = Dispatcher()
        handle = LoginHandler()
        test_dispatcher.add(handle)
        self.assertEqual(handle, test_dispatcher.handlers.get(CommunicationType.LOGIN))


if __name__ == '__main__':
    unittest.main()
