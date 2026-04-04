"""
Checks the input messages and calls appropriate handlers
"""
from Server.Data.AuthenticatedUsers import AuthenticatedUsers
from Server.Enums.Communication import Communication
from Server.Handlers.CreateAccountHandler import CreateAccountHandler
from Server.Handlers.LoginHandler import LoginHandler


class Dispatcher:
    """
    Constructor for the Dispatcher object
    """
    def __init__(self):
        self.handlers = dict()
        self.authenticated_users = AuthenticatedUsers()

    """
    Adds a handler to the handlers list
    
    Args:
        self that allows access to handlers.
        handler the message to be added.
    """
    def add(self, handler):
        self.handlers[handler.reqtype] = handler

    """
    Checks the input messages and calls appropriate handlers.
    
    Args:
        self that allows access to handlers.
        message the message to be parsed.
        
    Returns:
        a dictionary with appropriate content based on inputted message.
    """
    def dispatch(self, message):
        response = dict()
        if not isinstance(message, dict):
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        if message.get(Communication.REQUEST) is None:
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        else:
            reqtype = message[Communication.REQUEST]
            handler = self.handlers.get(reqtype)
            if handler is None:
                response[Communication.RESPONSE] = "MISSING_HANDLER"
                return response
            if isinstance(handler, LoginHandler) or isinstance(handler, CreateAccountHandler):
                message["authUsers"] = self.authenticated_users
            return handler.handle(message)