import unittest

from Server.Data.Planner import Planner


class TestConstructor(unittest.TestCase):

    def test_constructor(self):
        newPlanner = Planner()
        expected = {}
        actual = newPlanner.plannedMeals

        self.assertDictEqual(expected, actual)

if __name__ == '__main__':
    unittest.main()
