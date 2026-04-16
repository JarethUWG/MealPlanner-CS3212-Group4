import unittest

from Server.Enums.Communication import Communication
from Server.Handlers.GetMealHandler import GetMealHandler
from Server.Enums.CommunicationType import CommunicationType



class TestHandle(unittest.TestCase):
    def test_when_empty_dictionary(self):
        handler = GetMealHandler()
        message = {}

        expected = NotImplemented
        actual = handler.handle(message)

        self.assertEqual(expected, actual)

    def test_when_populated_dictionary(self):
        handler = GetMealHandler()
        message = {Communication.REQUEST: "GET MEAL", "other information": None}

        expected = NotImplemented
        actual = handler.handle(message)

        self.assertEqual(expected, actual)

    def test_handle_when_message_is_not_dictionary(self):
        handler = GetMealHandler()
        message = None

        with self.assertRaises(TypeError):
            handler.handle(message)


if __name__ == '__main__':
    unittest.main()
