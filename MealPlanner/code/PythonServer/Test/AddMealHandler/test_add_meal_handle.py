import unittest
import json
from Server.Data.User import User
from Server.Enums.Communication import Communication
from Server.Handlers.AddMealHandler import AddMealHandler

class TestHandle(unittest.TestCase):
    def test_message_not_dict(self):
        handle = AddMealHandler()
        message = "wrong data"
        response = handle.handle(message)
        self.assertEqual("BAD_INPUT", response[Communication.RESPONSE])

    def test_message_lacks_id(self):
        handle = AddMealHandler()
        message = dict()
        inputUser = User("username", "password")
        sessions = dict()
        sessions[10] = inputUser
        message["sessions"] = sessions
        message["meal"] = json.dumps({"ingredients":[{"name":"Milk","calories":40.0},{"name":"Tomato","calories":60.0},{"name":"Beef","calories":250.0}],"name":"Test Meal","description":"desc"})
        message["time"] = 100019
        response = handle.handle(message)
        self.assertEqual("BAD_INPUT", response[Communication.RESPONSE])

    def test_message_id_is_not_int(self):
        handle = AddMealHandler()
        message = dict()
        inputUser = User("username", "password")
        sessions = dict()
        sessions[10] = inputUser
        message["id"] = "10"
        message["sessions"] = sessions
        message["meal"] = json.dumps({"ingredients":[{"name":"Milk","calories":40.0},{"name":"Tomato","calories":60.0},{"name":"Beef","calories":250.0}],"name":"Test Meal","description":"desc"})
        message["time"] = 100019
        response = handle.handle(message)
        self.assertEqual("BAD_INPUT", response[Communication.RESPONSE])

    def test_message_lacks_sessions(self):
        handle = AddMealHandler()
        message = dict()
        inputUser = User("username", "password")
        sessions = dict()
        sessions[10] = inputUser
        message["id"] = 10
        message["meal"] = json.dumps({"ingredients":[{"name":"Milk","calories":40.0},{"name":"Tomato","calories":60.0},{"name":"Beef","calories":250.0}],"name":"Test Meal","description":"desc"})
        message["time"] = 100019
        response = handle.handle(message)
        self.assertEqual("SYSTEM_ERROR", response[Communication.RESPONSE])

    def test_message_lacks_time(self):
        handle = AddMealHandler()
        message = dict()
        inputUser = User("username", "password")
        sessions = dict()
        sessions[10] = inputUser
        message["sessions"] = sessions
        message["id"] = 10
        message["meal"] = json.dumps({"ingredients":[{"name":"Milk","calories":40.0},{"name":"Tomato","calories":60.0},{"name":"Beef","calories":250.0}],"name":"Test Meal","description":"desc"})
        response = handle.handle(message)
        self.assertEqual("BAD_INPUT", response[Communication.RESPONSE])

    def test_message_time_is_not_int(self):
        handle = AddMealHandler()
        message = dict()
        inputUser = User("username", "password")
        sessions = dict()
        sessions[10] = inputUser
        message["sessions"] = sessions
        message["id"] = 10
        message["meal"] = json.dumps({"ingredients":[{"name":"Milk","calories":40.0},{"name":"Tomato","calories":60.0},{"name":"Beef","calories":250.0}],"name":"Test Meal","description":"desc"})
        message["time"] = "99818211"
        response = handle.handle(message)
        self.assertEqual("BAD_INPUT", response[Communication.RESPONSE])

    def test_message_lacks_meal(self):
        handle = AddMealHandler()
        message = dict()
        inputUser = User("username", "password")
        sessions = dict()
        sessions[10] = inputUser
        message["sessions"] = sessions
        message["id"] = 10
        message["time"] = "99818211"
        response = handle.handle(message)
        self.assertEqual("BAD_INPUT", response[Communication.RESPONSE])

    def test_no_value_of_id(self):
        handle = AddMealHandler()
        message = dict()
        inputUser = User("username", "password")
        sessions = dict()
        sessions[10] = inputUser
        message["sessions"] = sessions
        message["id"] = 7
        message["meal"] = json.dumps({"ingredients":[{"name":"Milk","calories":40.0},{"name":"Tomato","calories":60.0},{"name":"Beef","calories":250.0}],"name":"Test Meal","description":"desc"})
        message["time"] = 100019
        response = handle.handle(message)
        self.assertEqual("INVALID", response[Communication.RESPONSE])

    def test_correct_input(self):
        handle = AddMealHandler()
        message = dict()
        inputUser = User("username", "password")
        sessions = dict()
        sessions[10] = inputUser
        message["sessions"] = sessions
        message["id"] = 10
        message["meal"] = json.dumps({"ingredients":[{"name":"Milk","calories":40.0},{"name":"Tomato","calories":60.0},{"name":"Beef","calories":250.0}],"name":"Test Meal","description":"desc"})
        message["time"] = 99818211
        response = handle.handle(message)
        self.assertEqual("VALID", response[Communication.RESPONSE])

if __name__ == '__main__':
    unittest.main()