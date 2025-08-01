lucide.createIcons();

const chatForm = document.getElementById("chat-form");
const userInput = document.getElementById("user-input");
const chatContainer = document.getElementById("chat-container");
const loadingIndicator = document.getElementById("loading-indicator");

const appendMessage = (sender, text) => {
  const messageWrapper = document.createElement("div");
  messageWrapper.className = `flex mb-2 ${
    sender === "user" ? "justify-end" : "justify-start"
  }`;

  const messageBubble = document.createElement("div");
  messageBubble.className = `p-3 rounded-2xl shadow-md max-w-[80%] ${
    sender === "user"
      ? "bg-indigo-600 text-white rounded-br-none"
      : "bg-gray-800 text-gray-200 rounded-bl-none"
  }`;
  messageBubble.innerText = text;

  messageWrapper.appendChild(messageBubble);
  chatContainer.querySelector(".flex-col-reverse").prepend(messageWrapper);
  chatContainer.scrollTop = chatContainer.scrollHeight;
};

chatForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  const prompt = userInput.value.trim();

  if (prompt) {
    appendMessage("user", prompt);
    userInput.value = "";

    loadingIndicator.classList.remove("hidden");

    let chatHistory = [];
    chatHistory.push({ role: "user", parts: [{ text: prompt }] });
    const payload = { contents: chatHistory };

    const apiUrl = `https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-preview-05-20:generateContent?key=${apiKey}`;

    try {
      const response = await fetch(apiUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        throw new Error(`API error: ${response.status} ${response.statusText}`);
      }

      const result = await response.json();

      if (
        result.candidates &&
        result.candidates.length > 0 &&
        result.candidates[0].content &&
        result.candidates[0].content.parts &&
        result.candidates[0].content.parts.length > 0
      ) {
        const botResponse = result.candidates[0].content.parts[0].text;
        appendMessage("bot", botResponse);
      } else {
        appendMessage("bot", "Sorry, I couldn't generate a response.");
      }
    } catch (error) {
      console.error("Error calling Gemini API:", error);
      appendMessage("bot", "An error occurred. Please try again later.");
    } finally {
      loadingIndicator.classList.add("hidden");
    }
  }
});
