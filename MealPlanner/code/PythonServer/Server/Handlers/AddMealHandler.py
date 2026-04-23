from typing import Dict

from Server.Data.Ingredient import Ingredient
from Server.Data.Meal import Meal
from Server.Enums.Communication import Communication
from Server.Enums.CommunicationType import CommunicationType
from Server.Enums.MessageKey import MessageKey
from Server.Handlers.Handler import Handler
import json

class AddMealHandler(Handler):
    """
    Handler for adding a meal to a given planner.
    """

    def __init__(self):
        self.reqtype = CommunicationType.ADD_MEAL

    def handle(self, message):
        """
        Adds a meal to a planner, based on the request details.

        :param message: The message to handle.
        Should contain a serialized Meal, an epoch time to add it to (converted to an int), and the planner ID
        to add to.
        :return: Response indicating if the request passed through correctly.
        """
        response = dict()
        if not isinstance(message, Dict):
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        if MessageKey.SESSIONS not in message:
            response[Communication.RESPONSE] = "SYSTEM_ERROR"
            return response
        if MessageKey.ID not in message or not isinstance(message.get(MessageKey.ID), int):
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        if MessageKey.TIME not in message or not isinstance(message.get(MessageKey.TIME), int):
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        if MessageKey.MEAL not in message:
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        response[Communication.RESPONSE] = "INVALID"
        activeSessions: dict[int, object] = message.get(MessageKey.SESSIONS)
        userID = message.get(MessageKey.ID)
        user = activeSessions.get(userID)
        userPlanner = user.userPlanner if (user is not None) else None

        if userPlanner is not None:
            meal = json.loads(message.get(MessageKey.MEAL))
            timeToAdd = message.get(MessageKey.TIME)
            ingredients = list()
            for currIng in meal.get("ingredients"):
                addedIng = Ingredient(currIng.get("name"), currIng.get("calories"))
                ingredients.append(addedIng)
            addedMeal = Meal(ingredients, meal.get("name"), meal.get("description"))
            userPlanner.addMeal(timeToAdd, addedMeal)
            response[Communication.RESPONSE] = "VALID"
        return response