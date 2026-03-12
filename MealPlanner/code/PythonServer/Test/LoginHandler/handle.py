import unittest

from Server.LoginHandler import LoginHandler

class MyTestCase(unittest.TestCase):
    def testHandle(self):
        handle = LoginHandler()
        message = dict()
        message["anytype"] = "Message"
        response = handle.handle(message)
        self.assertEqual("UNIMPLEMENTED", response["restype"])


if __name__ == '__main__':
    unittest.main()
