from typing import List

from Server.Data.User import User

"""
Holds a list of authenticated users for the system.

@author Jareth Batty
@version Spring 2026
"""
class AuthenticatedUsers:

    """
    Instantiates a list of authenticated users.
    """
    def __init__(self):
        self._users = []
        matt = User("Username", "Password")
        self._users.append(matt)

    """
    Gets the current value of users.

    @return The current value of users.
    """
    def getUsers(self):
        return self._users

    """
    Adds a user to the list of users.
    @precondition user != null && !this.users.contains(user)
    @postcondition this.users.contains(user)
    @param user the user to be added.                                                                                                         
    """
    def addUser(self, user):
        if not isinstance(user, User):
            raise TypeError("user must be a User")
        if user in self._users:
            raise ValueError("Added user already exists in list.")
        self._users.append(user)

    """
    Updates a user in the list of users.
    @precondition user != null && 
                  _users contains a user with a name
                  and password matching the added user.
    @postcondition this.users.contains(user)
    @param user the user to be updated.                                                                                                         
    """
    def updateUser(self, user):
        if not isinstance(user, User):
            raise TypeError("user must be a User")
        for authUser in self._users:
            if user.getUsername() == authUser.getUsername() and user.getPassword() == authUser.getPassword():
                self._users.remove(authUser)
                self._users.append(user)
                return True
        return False