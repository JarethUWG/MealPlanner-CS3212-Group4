"""
Defines a user.

@author Jareth Batty
@version Spring 2026
"""
class User:

    """
    Initializes a new user.

    @precondition username != null && !username.isEmpty,
              password != null && !password.isEmpty,
    @postcondition this.username == username
               this.password == password
    @param username the username of the user.
    @param password the password of the user.
    """
    def __init__(self, username, password):
        if not isinstance(username, str):
            raise ValueError("Please input valid username")
        if not (username.strip()):
            raise ValueError("Please input valid username")
        if not isinstance(password, str):
            raise ValueError("Please input valid password")
        if not (password.strip()):
            raise ValueError("Please input valid password")
        "Uncomment when planner is added to server"
        "self._userPlanner = Planner()"
        self._username = username
        self._password = password

    """
    Gets the current value of username.
    
    @return The current value of username.
    """
    def getUsername(self):
        return self._username

    """
    Gets the current value of password.
    
    @return The current value of password.
    """
    def getPassword(self):
        return self._password

    """
    Gets the current user's planner.

    @return The current planner
    """
    """
    Uncomment when planner is added.
    def getUserPlanner(self) {
        return this.userPlanner;
    }
    """
