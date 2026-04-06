import unittest

from Server.Data.AuthenticatedUsers import AuthenticatedUsers
from Server.Data.User import User
from Server.Enums.Communication import Communication
from Server.Handlers.LoginHandler import LoginHandler

class TestHandle(unittest.TestCase):
    def test_message_not_dict(self):
        handle = LoginHandler()
        message = "wrong data"
        response = handle.handle(message)
        self.assertEqual("BAD_INPUT", response[Communication.RESPONSE])

    def test_message_lacks_password(self):
        handle = LoginHandler()
        message = dict()
        message["username"] = "username"
        message["authUsers"] = AuthenticatedUsers()
        message["sessions"] = dict()
        response = handle.handle(message)
        self.assertEqual("BAD_INPUT", response[Communication.RESPONSE])

    def test_message_lacks_username(self):
        handle = LoginHandler()
        message = dict()
        message["password"] = "password"
        message["authUsers"] = AuthenticatedUsers()
        message["sessions"] = dict()
        response = handle.handle(message)
        self.assertEqual("BAD_INPUT", response[Communication.RESPONSE])

    def test_message_lacks_auth_users(self):
        handle = LoginHandler()
        message = dict()
        message["username"] = "username"
        message["password"] = "password"
        message["sessions"] = dict()
        response = handle.handle(message)
        self.assertEqual("SYSTEM_ERROR", response[Communication.RESPONSE])

    def test_message_lacks_sessions(self):
        handle = LoginHandler()
        message = dict()
        message["username"] = "username"
        message["password"] = "password"
        message["authUsers"] = AuthenticatedUsers()
        response = handle.handle(message)
        self.assertEqual("SYSTEM_ERROR", response[Communication.RESPONSE])

    def test_password_doesnt_match(self):
        handle = LoginHandler()
        message = dict()
        message["username"] = "username"
        message["password"] = "wrong"
        authUsers = AuthenticatedUsers()
        inputUser = User("username", "password")
        authUsers.addUser(inputUser)
        message["authUsers"] = authUsers
        message["sessions"] = dict()
        response = handle.handle(message)
        self.assertEqual("INVALID", response[Communication.RESPONSE])

    def test_username_doesnt_match(self):
        handle = LoginHandler()
        message = dict()
        message["username"] = "wrong"
        message["password"] = "password"
        authUsers = AuthenticatedUsers()
        inputUser = User("username", "password")
        authUsers.addUser(inputUser)
        message["authUsers"] = authUsers
        message["sessions"] = dict()
        response = handle.handle(message)
        self.assertEqual("INVALID", response[Communication.RESPONSE])

    def test_correct_input(self):
        handle = LoginHandler()
        message = dict()
        message["username"] = "username"
        message["password"] = "password"
        authUsers = AuthenticatedUsers()
        inputUser = User("username", "password")
        authUsers.addUser(inputUser)
        message["authUsers"] = authUsers
        message["sessions"] = dict()
        response = handle.handle(message)
        id = response["id"]
        res_sessions = message["sessions"]
        output_user = res_sessions[id]
        self.assertEqual("VALID", response[Communication.RESPONSE])
        self.assertTrue(isinstance(id, int))
        self.assertEqual("username", output_user.getUsername())
        self.assertEqual("password", output_user.getPassword())



if __name__ == '__main__':
    unittest.main()
