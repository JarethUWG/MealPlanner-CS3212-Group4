from Server.Data.AuthenticatedUsers import AuthenticatedUsers
from Server.Enums.Communication import Communication
from Server.Enums.CommunicationType import CommunicationType
from Server.Enums.MessageKey import MessageKey
from Server.Handlers.Handler import Handler

class LogoutHandler(Handler):
    """
    Constructor for the LogoutHandler object.
    """
    def __init__(self):
        self.reqtype = CommunicationType.LOGOUT

    """
    Handles an incoming logout request.
    
    Args:
        self which is currently unused.
        message which contains an id and dict of active sessions.
    
    Returns:
        A response appropriate for the message.
    """
    def handle(self, message):
        response = dict()
        if not isinstance(message, dict):
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        if MessageKey.SESSIONS not in message:
            response[Communication.RESPONSE] = "SYSTEM_ERROR"
            return response
        if MessageKey.ID not in message or not isinstance(message.get(MessageKey.ID), int):
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        if MessageKey.TIME not in message or not isinstance(message.get(MessageKey.TIME), int):
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        if MessageKey.MEAL not in message:
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        userId = message.get(MessageKey.ID)
        sessions = message.get(MessageKey.SESSIONS)
        authUsers = message.get(MessageKey.AUTHENTICATED_USERS)
        response[Communication.RESPONSE] = "INVALID"
        if userId in sessions:
            response[Communication.RESPONSE] = "VALID"
            authUsers.updateUser(sessions[userId])
            sessions.pop(userId)
        return response
