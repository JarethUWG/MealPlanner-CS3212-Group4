from typing import Dict

from Server.Enums.Communication import Communication
from Server.Enums.CommunicationType import CommunicationType
from Server.Enums.MessageKey import MessageKey
from Server.Handlers.Handler import Handler


class GetPlannerHandler(Handler):
    """
    Dedicated handler for retrieving Planner information
    """

    @property
    def reqtype(self):
        return self._required_type

    def __init__(self):
        self._required_type = CommunicationType.GET_PLANNER

    def handle(self, message):
        """
        Retrieves a planner based on the request details.

        :param message: The message to handle. Must be a dictionary object
        :return: JSON-serialized Planner information
        :raise TypeError: If message is not a dictionary
        :raise KeyError: If MessageKey.SESSIONS is not in message
        :raise KeyError: If MessageKey.ID is not in message
        """
        if not isinstance(message, Dict):
            raise TypeError("Message must be a dictionary")
        if MessageKey.SESSIONS not in message:
            raise KeyError("Sessions key is missing from the received message")
        if MessageKey.ID not in message:
            raise KeyError("UserId key is missing from the received message")

        response = {Communication.RESPONSE: "INVALID"}
        activeSessions: dict[int, object] = message.get(MessageKey.SESSIONS)
        userID = message.get(MessageKey.ID)
        user = activeSessions.get(userID)
        userPlanner = user.getUserPlanner() if (user is not None) else None

        if userPlanner is not None:
            serializedPlanner = userPlanner.serialize()
            response = {Communication.RESPONSE: "VALID", MessageKey.PLANNER: serializedPlanner}

        return response