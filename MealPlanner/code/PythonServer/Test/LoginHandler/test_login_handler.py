import unittest

from Server.Handlers.LoginHandler import LoginHandler

class TestLoginHandler(unittest.TestCase):
    def test_constructor(self):
        handle = LoginHandler()
        self.assertEqual("LOGIN", handle.reqtype)


if __name__ == '__main__':
    unittest.main()
