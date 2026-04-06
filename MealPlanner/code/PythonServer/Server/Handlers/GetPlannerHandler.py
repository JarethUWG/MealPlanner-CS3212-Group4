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
        :raise KeyError: If
        """
        if not isinstance(message, Dict):
            raise TypeError("Message must be a dictionary")

        gotPlanner = message.get(MessageKey.SESSIONS)
        response = {Communication.RESPONSE: CommunicationType.GET_PLANNER, Communication.BODY: gotPlanner}

        return NotImplemented
