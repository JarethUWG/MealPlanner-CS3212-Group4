import unittest

from Server.Enums.Communication import Communication
from Server.LoginHandler import LoginHandler

class TestHandle(unittest.TestCase):
    def test_handle(self):
        handle = LoginHandler()
        message = dict()
        message["anytype"] = "Message"
        response = handle.handle(message)
        self.assertEqual("UNIMPLEMENTED", response[Communication.RESPONSE])


if __name__ == '__main__':
    unittest.main()
