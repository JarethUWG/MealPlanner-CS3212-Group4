import unittest
from Server.Dispatcher import Dispatcher

class TestDispatcher(unittest.TestCase):
    def test_constructor(self):
        test_dispatcher = Dispatcher()
        self.assertTrue(not test_dispatcher.handlers)


if __name__ == '__main__':
    unittest.main()
