from typing import Dict

from Server.Enums.Communication import Communication
from Server.Enums.CommunicationType import CommunicationType
from Server.Handler import Handler


class GetMealHandler(Handler):
    """
    Dedicated handler for retrieving Meal information
    """

    @property
    def reqtype(self):
        return self._required_type

    def __init__(self):
        self._required_type = CommunicationType.GET_MEAL

    def handle(self, message):
        """
        Retrieves a meal based on the request details.

        :param message: The message to handle. Must be a dictionary object
        :return: NotImplemented. When implemented it should return A JSON-serialized meal
        :raise TypeError: If message is not a dictionary
        """
        if not isinstance(message, Dict):
            raise TypeError("Message must be a dictionary")

        # TODO get meal from stored user-information once implemented and return response
        gotMeal = None
        response = {Communication.RESPONSE: CommunicationType.GET_MEAL, Communication.BODY: gotMeal}

        return NotImplemented
