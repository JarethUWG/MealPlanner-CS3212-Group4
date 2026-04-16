from enum import Enum

class MessageKey(str, Enum):
    USERNAME = "username"
    PASSWORD = "password"
    ID = "id"
    AUTHENTICATED_USERS = "authUsers"
    SESSIONS = "sessions"