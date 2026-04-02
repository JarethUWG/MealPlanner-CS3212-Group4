

class Ingredient:
    """
    Dataclass for storing ingredient-related information
    """

    @property
    def name(self):
        """
        The name of this ingredient
        """
        return self._name

    @property
    def calories(self):
        """
        The calories for this ingredient
        """
        return self._calories

    def __init__(self, name: str, calories: int | float):
        """
        Initializes a new ingredient
        :param name: The name
        :param calories: The clories of this ingredient
        :raises TypeError: name is not a string or calories is not a number
        :raises ValueError: If name is an empty string or calories is < 0
        """
        if not isinstance(name, str):
            raise TypeError("name must be a string")
        if len(name.strip()) == 0:
            raise ValueError("name must not be an empty string")
        if not isinstance(calories, (int, float)):
            raise TypeError("calories must be a number")
        if calories < 0:
            raise ValueError("calories must be >= 0")

        self._name = name
        self._calories = calories
