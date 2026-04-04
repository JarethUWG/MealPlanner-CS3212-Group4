import unittest

from Server.Data.Ingredient import Ingredient
from Server.Data.Meal import Meal
from Server.Data.Planner import Planner


class TestAddMeal(unittest.TestCase):

    def setUp(self):
        self._planner = Planner()

    def test_whenValidArguments(self):
        time = 0
        meal = Meal([], "Basic meal", "No ingredients")
        self._planner.addMeal(time, meal)

        expected = {0: [meal]}
        actual = self._planner.plannedMeals

        self.assertDictEqual(expected, actual)

    def test_WhenMealAlreadyPlannedAtGivenDateTime(self):
        time = 0
        firstMeal = Meal([], "Basic meal", "No ingredients")
        secondMeal = Meal([], "Another basic meal", "Also no ingredients")
        self._planner.addMeal(time, firstMeal)
        self._planner.addMeal(time, secondMeal)

        expected = {0: [firstMeal, secondMeal]}
        actual = self._planner.plannedMeals

        self.assertDictEqual(expected, actual)

    def test_throwsWhenDateTimeEpochIsNotAnInteger(self):
        validMeal = Meal([], "Basic meal", "No ingredients")

        with self.subTest("none"):
            with self.assertRaises(TypeError):
                self._planner.addMeal(None, validMeal)
        with self.subTest("float"):
            with self.assertRaises(TypeError):
                self._planner.addMeal(0.2, validMeal)
        with self.subTest("string"):
            with self.assertRaises(TypeError):
                self._planner.addMeal("2", validMeal)

    def test_throwsMealIsNotAMeal(self):
        validTime = 100

        with self.subTest("none"):
            with self.assertRaises(TypeError):
                self._planner.addMeal(validTime, None)
        with self.subTest("dictionary"):
            with self.assertRaises(TypeError):
                self._planner.addMeal(validTime, {"ingredients": ["bread"], "name": "Toast", "description": ""})
        with self.subTest("string"):
            with self.assertRaises(TypeError):
                self._planner.addMeal(validTime, "Im a meal")

if __name__ == '__main__':
    unittest.main()
