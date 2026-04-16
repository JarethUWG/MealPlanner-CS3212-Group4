import unittest

from Server.Data.Meal import Meal
from Server.Data.User import User
from Server.Enums.Communication import Communication
from Server.Enums.CommunicationType import CommunicationType
from Server.Enums.MessageKey import MessageKey
from Server.Handlers.GetPlannerHandler import GetPlannerHandler


class TestHandle(unittest.TestCase):
    def test_throws_when_empty_dictionary(self):
        handler = GetPlannerHandler()
        message = {}

        with self.assertRaises(KeyError):
            handler.handle(message)

    def test_throws_when_missing_sessions_key(self):
        handler = GetPlannerHandler()
        message = {MessageKey.ID: 0}

        with self.assertRaises(KeyError):
            handler.handle(message)

    def test_throws_when_missing_userid_key(self):
        handler = GetPlannerHandler()
        message = {MessageKey.SESSIONS: {}}

        with self.assertRaises(KeyError):
            handler.handle(message)

    def test_when_populated_dictionary(self):
        handler = GetPlannerHandler()
        message = {
            Communication.REQUEST: CommunicationType.GET_PLANNER,
            MessageKey.SESSIONS: {},
            MessageKey.ID: 0
        }

        expected = "INVALID"
        actual = handler.handle(message).get(Communication.RESPONSE)

        self.assertEqual(expected, actual)

    def test_throws_when_message_is_not_dictionary(self):
        handler = GetPlannerHandler()
        message = None

        with self.assertRaises(TypeError):
            handler.handle(message)


if __name__ == '__main__':
    unittest.main()