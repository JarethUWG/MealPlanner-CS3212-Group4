import unittest
from Server.Dispatcher import Dispatcher

class MyTestCase(unittest.TestCase):
    def testConstructor(self):
        test_dispatcher = Dispatcher()
        self.assertTrue(not test_dispatcher.handlers)


if __name__ == '__main__':
    unittest.main()
