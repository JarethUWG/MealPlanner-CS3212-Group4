from typing import Dict

from Server.Data.Ingredient import Ingredient
from Server.Data.Meal import Meal
from Server.Enums.Communication import Communication
from Server.Enums.CommunicationType import CommunicationType
from Server.Enums.MessageKey import MessageKey
from Server.Enums.ResponseType import ResponseType
from Server.Handlers.Handler import Handler

class AddMealHandler(Handler):
    """
    Constructor for the AddMealHandler object.
    """

    def __init__(self):
        self.reqtype = CommunicationType.ADD_MEAL

    """
    Adds a meal to the current session's planner.
    
    Args:
        self which is currently unused.
        Message will have a message ID, a date to add to, and a meal to add.
    """

    def handle(self, message):
        response = dict()
        if not isinstance(message, Dict):
            response[Communication.RESPONSE] = ResponseType.BAD_INPUT
            return response
        if MessageKey.SESSIONS not in message:
            response[Communication.RESPONSE] = ResponseType.BAD_INPUT
            return response
        if MessageKey.ID not in message:
            response[Communication.RESPONSE] = ResponseType.BAD_INPUT
            return response
        if not isinstance(message.get(MessageKey.NAME), str):
            response[Communication.RESPONSE] = ResponseType.BAD_INPUT
            return response
        if not isinstance(message.get(MessageKey.DESCRIPTION), str):
            response[Communication.RESPONSE] = ResponseType.BAD_INPUT
            return response
        if not isinstance(message.get(MessageKey.INGREDIENTS), list):
            response[Communication.RESPONSE] = ResponseType.BAD_INPUT
            return response
        response = {Communication.RESPONSE: ResponseType.INVALID}
        activeSessions: dict[int, object] = message.get(MessageKey.SESSIONS)
        userID = message.get(MessageKey.ID)
        user = activeSessions.get(userID)
        userPlanner = user.getUserPlanner() if (user is not None) else None
        if userPlanner is not None:
            ingredients = list()
            for ingData in message.get(MessageKey.INGREDIENTS):
                newIng = Ingredient(ingData.get(MessageKey.NAME), ingData.get(MessageKey.CALORIES))
                ingredients.append(newIng)
            newMeal = Meal(ingredients, message.get(MessageKey.NAME), message.get(MessageKey.DESCRIPTION))
            userPlanner.add(message.get(MessageKey.DATE), newMeal)
            response[Communication.RESPONSE] = ResponseType.VALID

        return response

