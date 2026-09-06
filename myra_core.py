class MyraAI:
    def __init__(self):
        self.name = "Myra"
        self.is_active = True

    def process_command(self, user_input):
        command = user_input.lower().strip()

        if command in ["exit", "quit", "bye"]:
            self.is_active = False
            return "Goodbye! Have a great day."

        if "hello" in command or "hi" in command:
            return f"Hello! I am {self.name}, your AI assistant. How can I help you today?"

        if "your name" in command:
            return "My name is Myra."

        return f"I received your command: '{user_input}'."

    def run(self):
        print(f"{self.name} is online and listening...")

        while self.is_active:
            try:
                user_input = input("You: ")
                response = self.process_command(user_input)
                print(f"{self.name}: {response}")

            except KeyboardInterrupt:
                print("\nShutting down Myra...")
                break


if __name__ == "__main__":
    MyraAI().run()
