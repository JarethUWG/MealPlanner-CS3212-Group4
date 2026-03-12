import unittest

from Server.LoginHandler import LoginHandler

class MyTestCase(unittest.TestCase):
    def testConstructor(self):
        handle = LoginHandler()
        self.assertEqual("LOGIN", handle.reqtype)


if __name__ == '__main__':
    unittest.main()
