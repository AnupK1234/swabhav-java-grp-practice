const API_KEY = "AIzaSyASJg-XphjE4J7yTOAZAqLDLUZbvz-8Oto"; // Your Gemini API key

// Get elements from the page
const sendButton = document.getElementById("send-btn");
const inputBox = document.getElementById("message-input");
const chatArea = document.getElementById("chat-body");
const messageTemplate = document.getElementById("msg-template");

// When "Send" is clicked
sendButton.addEventListener("click", sendMessage);

// Also send when user presses "Enter"
inputBox.addEventListener("keydown", function (event) {
  if (event.key === "Enter") {
    sendMessage();
  }
});

// Show message in chat
function showMessage(who, text) {
  const copy = messageTemplate.content.cloneNode(true);
  const bubble = copy.querySelector(".bubble");
  bubble.classList.add(who); 
  bubble.querySelector("span").textContent = text;
  chatArea.appendChild(copy);
  chatArea.scrollTop = chatArea.scrollHeight;
}

// Main function to send message and get Gemini reply
async function sendMessage() {
  const message = inputBox.value.trim();
  if (message === "") return;

  showMessage("user", message);
  inputBox.value = "";

  // Prepare request for Gemini
  const url = `https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=${API_KEY}`;
  const body = {
    contents: [
      {
        parts: [{ text: message }],
      },
    ],
  };

  try {
    const response = await fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    });

    const result = await response.json();
    const reply = result?.candidates?.[0]?.content?.parts?.[0]?.text || "No reply from Gemini.";
    showMessage("bot", reply);
  } catch (error) {
    console.log("Error:", error);
    showMessage("bot", "⚠️ Something went wrong.");
  }
}
