from enum import Enum

class ResponseType(str, Enum):
    BAD_INPUT = "BAD_INPUT"
    SYSTEM_ERROR = "SYSTEM_ERROR"
    INVALID = "INVALID"
    VALID = "VALID"
    MISSING_HANDLER = "MISSING_HANDLER"