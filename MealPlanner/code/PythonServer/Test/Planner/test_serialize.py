import unittest

from Server.Data.Ingredient import Ingredient
from Server.Data.Meal import Meal
from Server.Data.Planner import Planner


class TestSerialize(unittest.TestCase):

    def setUp(self):
        self._planner = Planner()

    def test_when_no_planned_meals(self):
        expected = {}
        actual = self._planner.serialize()

        self.assertDictEqual(expected, actual)

    def test_when_one_planned_meal(self):
        date = 3200
        meal = Meal([], "Air", "Has no ingredients")
        self._planner.addMeal(date, meal)

        expected = {date: [meal.serialize()]}
        actual = self._planner.serialize()

        self.assertDictEqual(expected, actual)

    def test_when_multiple_planned_meals_in_one_date(self):
        date = 1530
        breadIngredient = Ingredient("Bread", 80)
        cheeseIngredient = Ingredient("Cheese", 100)
        toast = Meal([breadIngredient], "Toast", "A slice of toast")
        stringCheese = Meal([cheeseIngredient], "String Cheese", "A piece of string cheese")
        self._planner.addMeal(date, toast)
        self._planner.addMeal(date, stringCheese)

        expected = {date: [toast.serialize(), stringCheese.serialize()]}
        actual = self._planner.serialize()

        self.assertDictEqual(expected, actual)

    def test_when_multiple_planned_meals_in_different_dates(self):
        dateOne = 1530
        dateTwo = 280
        breadIngredient = Ingredient("Bread", 80)
        cheeseIngredient = Ingredient("Cheese", 100)
        toast = Meal([breadIngredient], "Toast", "A slice of toast")
        stringCheese = Meal([cheeseIngredient], "String Cheese", "A piece of string cheese")
        self._planner.addMeal(dateOne, toast)
        self._planner.addMeal(dateTwo, stringCheese)

        expected = {dateOne: [toast.serialize()], dateTwo: [stringCheese.serialize()]}
        actual = self._planner.serialize()

        self.assertDictEqual(expected, actual)


if __name__ == '__main__':
    unittest.main()
