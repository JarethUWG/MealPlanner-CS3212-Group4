import unittest

from Server.Data.AuthenticatedUsers import AuthenticatedUsers
from Server.Data.User import User
from Server.Enums.Communication import Communication
from Server.Enums.MessageKey import MessageKey
from Server.Enums.ResponseType import ResponseType
from Server.Handlers.LoginHandler import LoginHandler

class TestHandle(unittest.TestCase):
    def test_message_not_dict(self):
        handle = LoginHandler()
        message = "wrong data"
        response = handle.handle(message)
        self.assertEqual(ResponseType.BAD_INPUT, response[Communication.RESPONSE])

    def test_message_lacks_password(self):
        handle = LoginHandler()
        message = dict()
        message[MessageKey.USERNAME] = "username"
        message[MessageKey.AUTHENTICATED_USERS] = AuthenticatedUsers()
        message[MessageKey.SESSIONS] = dict()
        response = handle.handle(message)
        self.assertEqual(ResponseType.BAD_INPUT, response[Communication.RESPONSE])

    def test_message_lacks_username(self):
        handle = LoginHandler()
        message = dict()
        message[MessageKey.PASSWORD] = "password"
        message[MessageKey.AUTHENTICATED_USERS] = AuthenticatedUsers()
        message[MessageKey.SESSIONS] = dict()
        response = handle.handle(message)
        self.assertEqual(ResponseType.BAD_INPUT, response[Communication.RESPONSE])

    def test_message_lacks_auth_users(self):
        handle = LoginHandler()
        message = dict()
        message[MessageKey.USERNAME] = "username"
        message[MessageKey.PASSWORD] = "password"
        message[MessageKey.SESSIONS] = dict()
        response = handle.handle(message)
        self.assertEqual(ResponseType.SYSTEM_ERROR, response[Communication.RESPONSE])

    def test_message_lacks_sessions(self):
        handle = LoginHandler()
        message = dict()
        message[MessageKey.USERNAME] = "username"
        message[MessageKey.PASSWORD] = "password"
        message[MessageKey.AUTHENTICATED_USERS] = AuthenticatedUsers()
        response = handle.handle(message)
        self.assertEqual(ResponseType.SYSTEM_ERROR, response[Communication.RESPONSE])

    def test_password_doesnt_match(self):
        handle = LoginHandler()
        message = dict()
        message[MessageKey.USERNAME] = "username"
        message[MessageKey.PASSWORD] = "wrong"
        authUsers = AuthenticatedUsers()
        inputUser = User("username", "password")
        authUsers.addUser(inputUser)
        message[MessageKey.AUTHENTICATED_USERS] = authUsers
        message[MessageKey.SESSIONS] = dict()
        response = handle.handle(message)
        self.assertEqual(ResponseType.INVALID, response[Communication.RESPONSE])

    def test_username_doesnt_match(self):
        handle = LoginHandler()
        message = dict()
        message[MessageKey.USERNAME] = "wrong"
        message[MessageKey.PASSWORD] = "password"
        authUsers = AuthenticatedUsers()
        inputUser = User("username", "password")
        authUsers.addUser(inputUser)
        message[MessageKey.AUTHENTICATED_USERS] = authUsers
        message[MessageKey.SESSIONS] = dict()
        response = handle.handle(message)
        self.assertEqual(ResponseType.INVALID, response[Communication.RESPONSE])

    def test_correct_input(self):
        handle = LoginHandler()
        message = dict()
        message[MessageKey.USERNAME] = "username"
        message[MessageKey.PASSWORD] = "password"
        authUsers = AuthenticatedUsers()
        inputUser = User("username", "password")
        authUsers.addUser(inputUser)
        message[MessageKey.AUTHENTICATED_USERS] = authUsers
        message[MessageKey.SESSIONS] = dict()
        response = handle.handle(message)
        id = response[MessageKey.ID]
        res_sessions = message[MessageKey.SESSIONS]
        output_user = res_sessions[id]
        self.assertEqual(ResponseType.VALID, response[Communication.RESPONSE])
        self.assertTrue(isinstance(id, int))
        self.assertEqual("username", output_user.getUsername())
        self.assertEqual("password", output_user.getPassword())



if __name__ == '__main__':
    unittest.main()
