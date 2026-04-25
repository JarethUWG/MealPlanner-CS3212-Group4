from enum import Enum

class MessageKey(str, Enum):
    PLANNER = "planner"
    USERNAME = "username"
    PASSWORD = "password"
    ID = "id"
    AUTHENTICATED_USERS = "authUsers"
    SESSIONS = "sessions"
    MEAL = "meal"
    TIME = "time"
