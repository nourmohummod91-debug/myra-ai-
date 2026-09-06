import os
from flask import Flask, request, jsonify
from openai import OpenAI

app = Flask(__name__)

api_key = os.environ.get("OPENAI_API_KEY")

if not api_key:
    raise RuntimeError("OPENAI_API_KEY is not configured")

client = OpenAI(api_key=api_key)


@app.route("/chat", methods=["POST"])
def chat():
    data = request.get_json(silent=True) or {}
    message = str(data.get("message", "")).strip()

    if not message:
        return jsonify({
            "error": "Message is empty"
        }), 400

    try:
        response = client.responses.create(
            model="gpt-5.6",
            instructions=(
                "You are Myra, a friendly personal AI assistant. "
                "Answer naturally and clearly. "
                "If the user speaks Bengali, answer in Bengali. "
                "If the user speaks English, answer in English. "
                "Keep answers helpful and easy to understand."
            ),
            input=message
        )

        return jsonify({
            "reply": response.output_text
        })

    except Exception:
        return jsonify({
            "error": "AI request failed"
        }), 500


@app.route("/", methods=["GET"])
def home():
    return "Myra AI server is running!"


if __name__ == "__main__":
    app.run(
        host="0.0.0.0",
        port=int(os.environ.get("PORT", 8080))
      )
