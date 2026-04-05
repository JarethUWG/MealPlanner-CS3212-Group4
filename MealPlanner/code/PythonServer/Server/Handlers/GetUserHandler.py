from Server.Enums.Communication import Communication
from Server.Enums.CommunicationType import CommunicationType
from Server.Handlers.Handler import Handler

class GetUserHandler(Handler):
    """
    Constructor for the GetUserHandler object.
    """
    def __init__(self):
        self.reqtype = CommunicationType.GET_USER

    """
    Handles an incoming get user request.
    
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
            response[Communication.RESPONSE] = "SYSTEM_ERROR"
            return response
        else:
            id = message.get("id")
        if not isinstance(message.get("sessions"), dict):
            response[Communication.RESPONSE] = "SYSTEM_ERROR"
            return response
        else:
            sessions = message.get("sessions")
        response[Communication.RESPONSE] = "INVALID"
        if not isinstance(sessions[id], None):
            response[Communication.RESPONSE] = "VALID"
            sessions.pop(id)
        return response