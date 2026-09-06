from datetime import datetime


def get_current_time():
    """Returns formatted current time."""
    return datetime.now().strftime("%Y-%m-%d %H:%M:%S")


def log_message(message):
    """Simple logger for debugging."""
    timestamp = get_current_time()
    print(f"[{timestamp}] LOG: {message}")
