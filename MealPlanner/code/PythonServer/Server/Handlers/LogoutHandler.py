from Server.Enums.Communication import Communication
from Server.Enums.CommunicationType import CommunicationType
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
        if not isinstance(message.get("id"), int):
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        else:
            id = message.get("id")
        if not isinstance(message.get("sessions"), dict):
            response[Communication.RESPONSE] = "SYSTEM_ERROR"
            return response
        else:
            sessions = message.get("sessions")
        response[Communication.RESPONSE] = "INVALID"
        if id in sessions:
            response[Communication.RESPONSE] = "VALID"
            sessions.pop(id)
        return response