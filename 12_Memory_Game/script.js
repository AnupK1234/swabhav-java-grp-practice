const emojiSet = ["🦊", "🐸", "🦄", "🧸", "🐧", "🐙", "🐥", "🦕", "🦉", "🦈"];
const totalPairs = 10;
let board = [];
let flippedCards = [];
let matchedCount = 0;
let userClicks = 0;

const boardContainer = document.getElementById("game-area");
const messageBox = document.getElementById("win-message");

// Duplicate and shuffle emojis
function prepareBoard() {
  board = [...emojiSet, ...emojiSet]
    .sort(() => Math.random() - 0.5);
  board.forEach((emoji, idx) => createCard(emoji, idx));
}

// Create card elements
function createCard(symbol, index) {
  const card = document.createElement("div");
  card.classList.add("card");
  card.dataset.emoji = symbol;
  card.dataset.index = index;

  card.innerHTML = `
    <div class="card-inner">
      <div class="card-front">❓</div>
      <div class="card-back">${symbol}</div>
    </div>
  `;

  card.addEventListener("click", () => flipCard(card));
  boardContainer.appendChild(card);
}

// Card click logic
function flipCard(cardElement) {
  //If the card is already flipped, or if two cards are already flipped and waiting
  if (
    cardElement.classList.contains("flipped") ||
    flippedCards.length === 2
  ) return;

  userClicks++;
  cardElement.classList.add("flipped");
  flippedCards.push(cardElement);

  if (flippedCards.length === 2) {
    const [card1, card2] = flippedCards;
    if (card1.dataset.emoji === card2.dataset.emoji) {
      matchedCount++;
      flippedCards = [];

      if (matchedCount === totalPairs) {
        setTimeout(() => {
          messageBox.innerHTML = `🎉 Congratulations! You matched all in ${userClicks} clicks!`;
          messageBox.style.display = "block";
        }, 500);
      }
    } else {
      setTimeout(() => {
        card1.classList.remove("flipped");
        card2.classList.remove("flipped");
        flippedCards = [];
      }, 1000);
    }
  }
}

prepareBoard();
