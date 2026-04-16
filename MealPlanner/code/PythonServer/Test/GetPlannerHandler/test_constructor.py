import unittest

from Server.Handlers.GetPlannerHandler import GetPlannerHandler
from Server.Enums.CommunicationType import CommunicationType


class TestConstructor(unittest.TestCase):
    def test_required_type(self):
        handler = GetPlannerHandler()

        expected = CommunicationType.GET_PLANNER
        actual = handler.reqtype

        self.assertEqual(expected, actual)


if __name__ == '__main__':
    unittest.main()