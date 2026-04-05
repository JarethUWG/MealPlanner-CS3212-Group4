import unittest

from Server.Data.Ingredient import Ingredient


class TestSerialize(unittest.TestCase):

    def test_Serialization(self):
        ingredient = Ingredient("Carrot", 50)

        expected = {"name": ingredient.name, "calories": ingredient.calories}
        actual = ingredient.serialize()

        self.assertDictEqual(expected, actual)


if __name__ == '__main__':
    unittest.main()
