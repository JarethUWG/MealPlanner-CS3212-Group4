from enum import Enum

class Communication(str, Enum):
    REQUEST = "reqtype"
    RESPONSE = "restype"