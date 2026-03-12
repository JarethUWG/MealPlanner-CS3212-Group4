import unittest
from Server.Dispatcher import Dispatcher
from Server.LoginHandler import LoginHandler


class MyTestCase(unittest.TestCase):
    def testAdd(self):
        test_dispatcher = Dispatcher()
        handle = LoginHandler()
        test_dispatcher.add(handle)
        self.assertEqual(handle, test_dispatcher.handlers.get("LOGIN"))


if __name__ == '__main__':
    unittest.main()
