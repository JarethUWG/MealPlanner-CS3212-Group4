import unittest

from Server.GetMealHandler import GetMealHandler


class TestConstructor(unittest.TestCase):
    def test_required_type(self):
        handler = GetMealHandler()

        expected = "GET MEAL"
        actual = handler.reqtype

        self.assertEqual(expected, actual)


if __name__ == '__main__':
    unittest.main()