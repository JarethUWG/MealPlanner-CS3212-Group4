import random

from Server.Data.AuthenticatedUsers import AuthenticatedUsers
from Server.Enums.Communication import Communication
from Server.Enums.CommunicationType import CommunicationType
from Server.Enums.MessageKey import MessageKey
from Server.Handlers.Handler import Handler

class LoginHandler(Handler):
    """
    Constructor for the LoginHandler object.
    """
    def __init__(self):
        self.reqtype = CommunicationType.LOGIN

    """
    Handles an incoming login request.
    
    Args:
        self which is currently unused.
        message which contains a username and password, and authenticated users.
        message which contains a username and password, authenticated users, and a dict of sessions.
    
    Returns:
        A response appropriate for the message.
    """
    def handle(self, message):
        response = dict()
        if not isinstance(message, dict) or not self._message_contains_username_and_password(message):
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        if not isinstance(message.get(MessageKey.AUTHENTICATED_USERS), AuthenticatedUsers):
            response[Communication.RESPONSE] = "SYSTEM_ERROR"
            return response
        if not isinstance(message.get(MessageKey.SESSIONS), dict):
            response[Communication.RESPONSE] = "SYSTEM_ERROR"
            return response

        response[Communication.RESPONSE] = "INVALID"
        username = message.get(MessageKey.USERNAME)
        password = message.get(MessageKey.PASSWORD)
        auth_users = message.get(MessageKey.AUTHENTICATED_USERS)
        sessions = message[MessageKey.SESSIONS]
        for user in auth_users.getUsers():
            if user.getUsername() == username and user.getPassword() == password:
                response[Communication.RESPONSE] = "VALID"
                generated_id = random.randint(-(2 ** 31), (2 ** 31 - 1))
                sessions[generated_id] = user
                response[MessageKey.ID] = generated_id
        return response

    def _message_contains_username_and_password(self, message: dict):
        return (MessageKey.USERNAME in message) and (MessageKey.PASSWORD in message)