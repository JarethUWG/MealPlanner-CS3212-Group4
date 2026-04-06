import unittest

from Server.Data.AuthenticatedUsers import AuthenticatedUsers
from Server.Data.User import User
from Server.Enums.Communication import Communication
from Server.Enums.MessageKey import MessageKey
from Server.Handlers.LogoutHandler import LogoutHandler

class TestHandle(unittest.TestCase):
    def test_message_not_dict(self):
        handle = LogoutHandler()
        message = "wrong data"
        response = handle.handle(message)
        self.assertEqual("BAD_INPUT", response[Communication.RESPONSE])

    def test_message_lacks_id(self):
        handle = LogoutHandler()
        message = dict()
        inputUser = User("username", "password")
        sessions = dict()
        sessions[10] = inputUser
        response = handle.handle(message)
        self.assertEqual("BAD_INPUT", response[Communication.RESPONSE])

    def test_message_lacks_sessions(self):
        handle = LogoutHandler()
        message = dict()
        message[MessageKey.ID] = 10
        response = handle.handle(message)
        self.assertEqual("SYSTEM_ERROR", response[Communication.RESPONSE])

    def test_no_value_of_id(self):
        handle = LogoutHandler()
        message = dict()
        message[MessageKey.ID] = 7
        inputUser = User("username", "password")
        sessions = dict()
        sessions[10] = inputUser
        message[MessageKey.SESSIONS] = sessions
        response = handle.handle(message)
        self.assertEqual("INVALID", response[Communication.RESPONSE])
        self.assertTrue(10 in sessions)

    def test_correct_input(self):
        handle = LogoutHandler()
        message = dict()
        message[MessageKey.ID] = 10
        inputUser = User("username", "password")
        sessions = dict()
        sessions[10] = inputUser
        message[MessageKey.SESSIONS] = sessions
        response = handle.handle(message)
        self.assertEqual("VALID", response[Communication.RESPONSE])
        self.assertFalse(10 in sessions)

if __name__ == '__main__':
    unittest.main()
