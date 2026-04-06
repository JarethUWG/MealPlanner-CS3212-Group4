import unittest

from Server.Enums.Communication import Communication
from Server.Enums.CommunicationType import CommunicationType
from Server.Handlers.GetPlannerHandler import GetPlannerHandler


class TestHandle(unittest.TestCase):
    def test_when_empty_dictionary(self):
        handler = GetPlannerHandler()
        message = {}

        expected = NotImplemented
        actual = handler.handle(message)

        self.assertEqual(expected, actual)

    def test_when_populated_dictionary(self):
        handler = GetPlannerHandler()
        message = {Communication.REQUEST: CommunicationType.GET_PLANNER, "other information": None}

        expected = NotImplemented
        actual = handler.handle(message)

        self.assertEqual(expected, actual)

    def test_handle_when_message_is_not_dictionary(self):
        handler = GetPlannerHandler()
        message = None

        with self.assertRaises(TypeError):
            handler.handle(message)


if __name__ == '__main__':
    unittest.main()
