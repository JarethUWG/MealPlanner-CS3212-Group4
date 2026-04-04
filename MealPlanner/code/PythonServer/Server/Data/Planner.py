from Server.Data.Meal import Meal


class Planner:
    """
    Dataclass for storing meal-related information
    """

    @property
    def plannedMeals(self):
        """
        The ingredients in this meal
        """
        return self._plannedMeals

    def __init__(self):
        """
        Initializes a new planner
        """
        self._plannedMeals: dict[int, list[Meal]] = {}

    def addMeal(self, dateTimeEpoch: int, meal: Meal):
        """
        Plans the meal at the given time
        :param dateTimeEpoch: The datetime to plan it for
        :param meal: The meal to add
        :raises TypeError: If dateTimeEpoch is not an int or if meal is not a meal
        :postcondition:
        """
        if not isinstance(dateTimeEpoch, int):
            raise TypeError("dateTimeEpoch must be an integer")
        if not isinstance(meal, Meal):
            raise TypeError("meal must be a meal object")

        plannedMealsOnDate = self._plannedMeals.get(dateTimeEpoch)

        if plannedMealsOnDate is None:
            plannedMealsOnDate = [meal]
        else:
            plannedMealsOnDate.append(meal)

        self._plannedMeals.update({dateTimeEpoch: plannedMealsOnDate})
