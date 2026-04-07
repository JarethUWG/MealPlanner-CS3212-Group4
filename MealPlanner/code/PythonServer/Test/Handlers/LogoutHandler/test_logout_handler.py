import unittest

from Server.Enums.CommunicationType import CommunicationType
from Server.Handlers.LogoutHandler import LogoutHandler

class TestLoginHandler(unittest.TestCase):
    def test_constructor(self):
        handle = LogoutHandler()
        self.assertEqual(CommunicationType.LOGOUT, handle.reqtype)


if __name__ == '__main__':
    unittest.main()
