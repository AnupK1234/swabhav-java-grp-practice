const gameBoard = document.getElementById("game-board");
const moveCounterDisplay = document.getElementById("move-counter");
const restartButton = document.getElementById("restart-btn");
const gameOverModal = document.getElementById("game-over-modal");
const finalMovesDisplay = document.getElementById("final-moves");
const modalRestartButton = document.getElementById("modal-restart-btn");

const cardEmojis = ["⭐", "💖", "🔔", "⚡", "☁️", "🚀", "💎", "⚓", "☀️", "🎧"];

let gameCards = [];
let flippedCards = [];
let moves = 0;
let isGameLocked = false;
let matchedCount = 0;

const shuffleArray = (array) => {
  for (let i = array.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [array[i], array[j]] = [array[j], array[i]];
  }
  return array;
};

const initGame = () => {
  gameBoard.innerHTML = "";
  flippedCards = [];
  moves = 0;
  isGameLocked = false;
  matchedCount = 0;
  moveCounterDisplay.textContent = moves;
  gameOverModal.classList.add("hidden");

  const cardsToPair = cardEmojis.flatMap((emoji, index) => {
    const card = { id: index, icon: emoji };
    return [card, card];
  });

  gameCards = shuffleArray(cardsToPair);

  gameCards.forEach((card, index) => {
    const cardElement = document.createElement("div");
    cardElement.classList.add(
      "card-container",
      "relative",
      "aspect-square",
      "rounded-lg",
      "cursor-pointer",
      "transform",
      "hover:scale-105",
      "transition-transform",
      "duration-200"
    );
    cardElement.dataset.id = card.id;
    cardElement.dataset.index = index;

    cardElement.innerHTML = `
                    <div class="card-inner">
                        <div class="card-front bg-gray-700 flex items-center justify-center p-2 border border-gray-600 shadow-md">
                            <span class="text-gray-400 text-5xl">?</span>
                        </div>
                        <div class="card-back bg-gray-700 flex items-center justify-center p-2 border border-gray-600 shadow-md">
                            <span class="text-5xl">
                                ${card.icon}
                            </span>
                        </div>
                    </div>
                `;
    cardElement.addEventListener("click", handleCardClick);
    gameBoard.appendChild(cardElement);
  });
};

const handleCardClick = (event) => {
  const cardContainer = event.currentTarget;
  if (
    isGameLocked ||
    cardContainer.classList.contains("flipped") ||
    cardContainer.classList.contains("matched")
  ) {
    return;
  }

  cardContainer.classList.add("flipped");
  flippedCards.push(cardContainer);

  if (flippedCards.length === 2) {
    isGameLocked = true;
    moves++;
    moveCounterDisplay.textContent = moves;

    const [card1, card2] = flippedCards;
    const id1 = card1.dataset.id;
    const id2 = card2.dataset.id;

    if (id1 === id2) {
      card1.classList.add("matched");
      card2.classList.add("matched");
      card1.removeEventListener("click", handleCardClick);
      card2.removeEventListener("click", handleCardClick);
      flippedCards = [];
      isGameLocked = false;
      matchedCount += 2;
      checkGameOver();
    } else {
      setTimeout(() => {
        card1.classList.remove("flipped");
        card2.classList.remove("flipped");
        flippedCards = [];
        isGameLocked = false;
      }, 1000);
    }
  }
};

const checkGameOver = () => {
  if (matchedCount === 20) {
    finalMovesDisplay.textContent = moves;
    gameOverModal.classList.remove("hidden");
  }
};

restartButton.addEventListener("click", initGame);
modalRestartButton.addEventListener("click", initGame);

document.addEventListener("DOMContentLoaded", initGame);
