from Server.Data.AuthenticatedUsers import AuthenticatedUsers
from Server.Data.User import User
from Server.Enums.Communication import Communication
from Server.Enums.CommunicationType import CommunicationType
from Server.Enums.MessageKey import MessageKey
from Server.Enums.ResponseType import ResponseType
from Server.Handlers.Handler import Handler

class CreateAccountHandler(Handler):
    """
    Constructor for the CreateAccountHandler object.
    """
    def __init__(self):
        self.reqtype = CommunicationType.CREATE_ACCOUNT

    """
    Handles an incoming create request.
    
    Args:
        self which is currently unused.
        message which contains a username, password, and authenticated users.
    
    Returns:
        A response appropriate for the message.
    """
    def handle(self, message):
        response = dict()
        if not isinstance(message, dict):
            response[Communication.RESPONSE] = ResponseType.BAD_INPUT
            return response
        if not isinstance(message.get(MessageKey.USERNAME), str):
            response[Communication.RESPONSE] = ResponseType.BAD_INPUT
            return response
        else:
            username = message.get(MessageKey.USERNAME)
        if not isinstance(message.get(MessageKey.USERNAME), str):
            response[Communication.RESPONSE] = ResponseType.BAD_INPUT
            return response
        else:
            password = message.get(MessageKey.USERNAME)
        if not isinstance(message.get(MessageKey.AUTHENTICATED_USERS), AuthenticatedUsers):
            response[Communication.RESPONSE] = ResponseType.BAD_INPUT
            return response
        else:
            auth_users = message.get(MessageKey.AUTHENTICATED_USERS)
        response[Communication.RESPONSE] = ResponseType.VALID
        for user in auth_users.getUsers():
            if user.getUsername() == username:
                response[Communication.RESPONSE] = ResponseType.INVALID
        added_user = User(username, password)
        auth_users.addUser(added_user)
        return response