import unittest

from Server.Data.Ingredient import Ingredient
from Server.Data.Meal import Meal


class TestSerialize(unittest.TestCase):

    def test_WhenNoIngredients(self):
        meal = Meal([], "Air", "Has no ingredients")

        expected = {"name": meal.name, "description": meal.description, "ingredients": []}
        actual = meal.serialize()

        self.assertDictEqual(expected, actual)

    def test_WhenOneIngredient(self):
        watermelon = Ingredient("Watermelon", 1300)
        meal = Meal([watermelon], "Air", "Has no ingredients")

        expected = {
            "name": meal.name,
            "description": meal.description,
            "ingredients": [watermelon.serialize()]
        }
        actual = meal.serialize()

        self.assertDictEqual(expected, actual)

    def test_WhenMultipleIngredients(self):
        apple = Ingredient("Apple", 100)
        watermelon = Ingredient("Watermelon", 1300)
        meal = Meal([watermelon, apple], "Fruits", "A couple fruits")

        expected = {
            "name": meal.name,
            "description": meal.description,
            "ingredients": [watermelon.serialize(), apple.serialize()]
        }
        actual = meal.serialize()

        self.assertDictEqual(expected, actual)


if __name__ == '__main__':
    unittest.main()
