from Server.Enums.Communication import Communication
from Server.Handler import Handler

class LoginHandler(Handler):
    """
    Constructor for the LoginHandler object.
    """
    def __init__(self):
        self.reqtype = "LOGIN"

    """
    Handles an incoming login request. Note implementation needed.
    
    Args:
        self which is currently unused.
        message which is currently unused.
    
    Returns:
        A response appropriate for the message (UNIMPLEMENTED).
    """
    def handle(self, message):
        response = dict()
        response[Communication.RESPONSE] = "UNIMPLEMENTED"
        return response