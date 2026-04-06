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
        if not isinstance(message, dict) or MessageKey.ID not in message:
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        if not isinstance(message.get(MessageKey.SESSIONS), dict):
            response[Communication.RESPONSE] = "SYSTEM_ERROR"
            return response

        userId = message.get(MessageKey.ID)
        sessions = message.get(MessageKey.SESSIONS)
        response[Communication.RESPONSE] = "INVALID"
        if userId in sessions:
            response[Communication.RESPONSE] = "VALID"
            sessions.pop(userId)
        return response