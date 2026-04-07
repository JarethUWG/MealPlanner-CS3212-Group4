from enum import Enum

class MessageKey(str, Enum):
    USERNAME = "username"
    PASSWORD = "password"
    ID = "id"
    AUTHENTICATED_USERS = "authUsers"
    SESSIONS = "sessions"
    PLANNER = "planner"
    DATE = "date"
    NAME = "name"
    DESCRIPTION = "description"
    INGREDIENTS = "ingredients"
    CALORIES = "calories"