from Server.Ingredient import Ingredient


class Meal:
    """
    Dataclass for storing meal-related information
    """

    @property
    def ingredients(self):
        """
        The ingredients in this meal
        """
        return self._ingredients

    @property
    def name(self):
        """
        The name of this meal
        """
        return self._name

    @property
    def description(self):
        """
        The description for this meal
        """
        return self._description

    def __init__(self, ingredients: list[Ingredient], name: str, description: str):
        """
        Initializes a new meal
        :param ingredients: The ingredients in this meal
        :param name: The name
        :param description: A descsription of this meal
        :raises TypeError: If ingredients is not a list of Ingredients, name is not a string, or description is not a string
        :raises ValueError: If name is an empty string
        """
        if not isinstance(ingredients, list):
            raise TypeError("ingredients must be a list")
        if len([ingredient for ingredient in ingredients if not isinstance(ingredient, Ingredient)]) > 0:
            raise TypeError("All indexes of ingredients must be an Ingredient")
        if not isinstance(name, str):
            raise TypeError("name must be a string")
        if len(name.strip()) == 0:
            raise ValueError("name must not be an empty string")
        if not isinstance(description, str):
            raise TypeError("description must be a string")

        self._ingredients = ingredients
        self._name = name
        self._description = description
