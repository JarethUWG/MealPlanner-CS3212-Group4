import unittest

from Server.Ingredient import Ingredient


class TestConstructor(unittest.TestCase):

    def test_when_valid_arguments(self):
        name = "Valid Meal"
        calories = 20
        validIngredient = Ingredient(name, calories)

        with self.subTest("Name"):
            self.assertEqual(name, validIngredient.name)
        with self.subTest("Calories"):
            self.assertEqual(calories, validIngredient.calories)

    def test_raises_when_invalid_name(self):
        invalidName = None
        calories = 0

        with self.subTest("non-string"):
            with self.assertRaises(TypeError):
                Ingredient(invalidName, calories)
        with self.subTest("empty"):
            invalidName = ""
            with self.assertRaises(ValueError):
                Ingredient(invalidName, calories)
        with self.subTest("only spaces"):
            invalidName = "    "
            with self.assertRaises(ValueError):
                Ingredient(invalidName, calories)

    def test_raises_when_invalid_calories(self):
        name = "valid name"
        invalidCalories = None

        with self.subTest("non-number"):
            with self.assertRaises(TypeError):
                Ingredient(name, invalidCalories)
        with self.subTest("below zero"):
            invalidCalories = -1
            with self.assertRaises(ValueError):
                Ingredient(name, invalidCalories)


if __name__ == '__main__':
    unittest.main()
