from Server.Data.AuthenticatedUsers import AuthenticatedUsers
from Server.Enums.Communication import Communication
from Server.Enums.CommunicationType import CommunicationType
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
    
    Returns:
        A response appropriate for the message.
    """
    def handle(self, message):
        response = dict()
        if not isinstance(message, dict):
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        if not isinstance(message.get("username"), str):
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        else:
            username = message.get("username")
        if not isinstance(message.get("password"), str):
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        else:
            password = message.get("password")
        if not isinstance(message.get("authUsers"), AuthenticatedUsers):
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        else:
            auth_users = message.get("authUsers")
        for user in auth_users.getUsers():
            if user.getUsername() == username or user.getPassword() == password:
                response[Communication.RESPONSE] = "INVALID"
        response[Communication.RESPONSE] = "VALID"
        return response