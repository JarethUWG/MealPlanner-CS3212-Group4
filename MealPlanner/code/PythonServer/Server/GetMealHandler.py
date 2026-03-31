from typing import Dict

from Server.Handler import Handler


class GetMealHandler(Handler):
    """
    Dedicated handler for retrieving Meal information
    """

    @property
    def reqtype(self):
        return self._required_type

    def __init__(self):
        self._required_type = "GET MEAL"

    def handle(self, message):
        """
        Handles . Note implementation needed.

        :param message: The message to handle. Must be a dictionary object
        :return: NotImplemented. When implemented it should return A JSON-serialized meal
        :raise TypeError: If message is not a dictionary
        """
        if not isinstance(message, Dict):
            raise TypeError("Message must be a dictionary")

        return NotImplemented
