from enum import Enum

class CommunicationType(str, Enum):
    LOGIN = "LOGIN"
    GET_MEAL = "GET MEAL"