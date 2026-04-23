"""
Checks the input messages and calls appropriate handlers
"""
from Server.Data.AuthenticatedUsers import AuthenticatedUsers
from Server.Enums.Communication import Communication
from Server.Handlers import LogoutHandler
from Server.Enums.MessageKey import MessageKey
from Server.Handlers.AddMealHandler import AddMealHandler
from Server.Handlers.CreateAccountHandler import CreateAccountHandler
from Server.Handlers.Handler import Handler
from Server.Handlers.LoginHandler import LoginHandler
from Server.Handlers.LogoutHandler import LogoutHandler


class Dispatcher:
    """
    Constructor for the Dispatcher object
    """
    def __init__(self):
        self.handlers = dict()
        self.sessions = dict()
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
        if not isinstance(message, dict) or message.get(Communication.REQUEST) is None:
            response[Communication.RESPONSE] = "BAD_INPUT"
            return response
        reqtype = message[Communication.REQUEST]
        handler = self.handlers.get(reqtype)
        if handler is None:
            response[Communication.RESPONSE] = "MISSING_HANDLER"
            return response
        if Dispatcher._handlerNeedsAuthenticatedUsers(handler):
            message[MessageKey.AUTHENTICATED_USERS] = self.authenticated_users
        if Dispatcher._handlerNeedsActiveSessions(handler):
            message[MessageKey.SESSIONS] = self.sessions
        return handler.handle(message)

    @staticmethod
    def _handlerNeedsAuthenticatedUsers(handler: Handler):
        return isinstance(handler, (LoginHandler, CreateAccountHandler))

    @staticmethod
    def _handlerNeedsActiveSessions(handler: Handler):
        return isinstance(handler, (LoginHandler, LogoutHandler, AddMealHandler))