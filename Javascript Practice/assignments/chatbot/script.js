const API_KEY = ""; // Replace with your Gemini API key

async function sendMessage() {
  const input = document.getElementById("user-input");
  const message = input.value.trim();
  if (!message) return;

  appendMessage("user", message);
  input.value = "";

  try {
    const response = await fetch(
      "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" +
        API_KEY,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          contents: [{ parts: [{ text: message }] }],
        }),
      }
    );

    const data = await response.json();
    const aiReply =
      data?.candidates?.[0]?.content?.parts?.[0]?.text ||
      "No response from Gemini.";
    appendMessage("bot", aiReply);
  } catch (error) {
    console.error("API Error:", error);
    appendMessage("bot", "⚠️ Failed to reach Gemini API.");
  }
}

function appendMessage(role, text) {
  const msgContainer = document.getElementById("messages");
  const msgDiv = document.createElement("div");
  msgDiv.className = `message ${role}`;
  msgDiv.innerText = text;
  msgContainer.appendChild(msgDiv);
  msgContainer.scrollTop = msgContainer.scrollHeight;
}
