"""
Checks the input messages and calls appropriate handlers
"""
class Dispatcher:
    """
    Constructor for the Dispatcher object
    """
    def __init__(self):
        self.handlers = dict()

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
        if message.get("reqtype") is None:
            response["restype"] = "BAD_INPUT"
            return response
        else:
            reqtype = message["reqtype"]
            handler = self.handlers.get(reqtype)
            if handler is None:
                response["restype"] = "MISSING_HANDLER"
                return response
            return handler.handle(message)