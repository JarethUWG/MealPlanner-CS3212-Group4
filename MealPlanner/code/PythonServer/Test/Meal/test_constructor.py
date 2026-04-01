import unittest

from Server.Meal import Meal


class TestConstructor(unittest.TestCase):

    def test_when_valid_arguments(self):
        ingredients = ["ingredient 1", "ingredient 2"]
        name = "Valid Meal"
        description = "I describe a valid meal"
        validMeal = Meal(ingredients, name, description)

        with self.subTest("Ingredients"):
            self.assertEqual(ingredients, validMeal.ingredients)
        with self.subTest("Name"):
            self.assertEqual(name, validMeal.name)
        with self.subTest("Description"):
            self.assertEqual(description, validMeal.description)

    def test_when_nonlist_ingredient(self):
        with self.assertRaises(TypeError):
            Meal(None, "name", "description")

    def test_when_empty_ingredient_list(self):
        ingredients = []
        name = "Valid Meal"
        description = "I describe a valid meal"
        validMeal = Meal(ingredients, name, description)

        self.assertEqual(ingredients, validMeal.ingredients)

    def test_raises_when_ingredients_contains_nonstring_element(self):
        invalidIngredients = []
        name = "Valid Meal"
        description = "I describe a valid meal"

        with self.subTest("None"):
            invalidIngredients = [None]
            with self.assertRaises(TypeError):
                Meal(invalidIngredients, name, description)
        with self.subTest("int"):
            invalidIngredients = [3, 5, 10]
            with self.assertRaises(TypeError):
                Meal(invalidIngredients, name, description)
        with self.subTest("some string some not"):
            invalidIngredients = ["valid", 20, "hello", "ingredient", 5]
            with self.assertRaises(TypeError):
                Meal(invalidIngredients, name, description)

    def test_raises_when_invalid_name(self):
        ingredients = []
        invalidName = None
        description = "I describe a valid meal"

        with self.subTest("non-string"):
            with self.assertRaises(TypeError):
                Meal(ingredients, invalidName, description)
        with self.subTest("empty"):
            invalidName = ""
            with self.assertRaises(ValueError):
                Meal(ingredients, invalidName, description)
        with self.subTest("only spaces"):
            invalidName = "    "
            with self.assertRaises(ValueError):
                Meal(ingredients, invalidName, description)

    def test_raises_when_invalid_description(self):
        ingredients = []
        name = "valid name"
        invalidDescription = None

        with self.assertRaises(TypeError):
            Meal(ingredients, name, invalidDescription)


if __name__ == '__main__':
    unittest.main()
