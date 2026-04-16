from enum import Enum

class CommunicationType(str, Enum):
    LOGIN = "LOGIN"
    CREATE_ACCOUNT = "CREATE ACCOUNT"
    GET_MEAL = "GET MEAL"