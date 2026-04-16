import unittest

from Server.Handlers.GetMealHandler import GetMealHandler
from Server.Enums.CommunicationType import CommunicationType


class TestConstructor(unittest.TestCase):
    def test_required_type(self):
        handler = GetMealHandler()

        expected = CommunicationType.GET_MEAL
        actual = handler.reqtype

        self.assertEqual(expected, actual)


if __name__ == '__main__':
    unittest.main()