from typing import Dict

from Server.Enums.Communication import Communication
from Server.Enums.CommunicationType import CommunicationType
from Server.Enums.MessageKey import MessageKey
from Server.Handlers.Handler import Handler


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
        Should contain a serialized Meal, an epoch time to add it to, and the planner ID
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
        if MessageKey.ID not in message:
            response[Communication.RESPONSE] = "BAD INPUT"
            return response
        if MessageKey.TIME not in message:
            response[Communication.RESPONSE] = "BAD INPUT"
            return response
        if MessageKey.MEAL not in message:
            response[Communication.RESPONSE] = "BAD INPUT"
            return response
        response[Communication.RESPONSE] = "INVALID"
        activeSessions: dict[int, object] = message.get(MessageKey.SESSIONS)
        userID = message.get(MessageKey.ID)
        user = activeSessions.get(userID)
        userPlanner = user.userPlanner if (user is not None) else None

        if userPlanner is not None:
            print(message.get(MessageKey.MEAL))
            response[Communication.RESPONSE] = "VALID"
        return response