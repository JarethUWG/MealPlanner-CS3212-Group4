from enum import Enum

class CommunicationType(str, Enum):
    LOGIN = "LOGIN"
    LOGOUT = "LOGOUT"
    GET_USER = "GET USER"
    CREATE_ACCOUNT = "CREATE ACCOUNT"
    GET_MEAL = "GET MEAL"