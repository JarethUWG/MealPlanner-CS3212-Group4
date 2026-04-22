import unittest

from Server.Handlers.AddMealHandler import AddMealHandler

class TestConstructor(unittest.TestCase):
    def test_constructor(self):
        handle = AddMealHandler()
        self.assertEqual("ADD MEAL", handle.reqtype)


if __name__ == '__main__':
    unittest.main()