from abc import ABC, abstractmethod

"""
Abstract class for handler objects.
"""
class Handler(ABC):
    pass

    """
    A method meant to handle the process for the given input message.
    """
    @abstractmethod
    def handle(self, message):
        pass