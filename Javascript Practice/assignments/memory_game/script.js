const emojis = ["🏠", "🚗", "🐶", "🍎", "🚀", "🎈", "🐱", "📚", "🎮", "🌟"];
let cards = [...emojis, ...emojis]; // 10 pairs

// Shuffle cards
function shuffle(array) {
  for (let i = array.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [array[i], array[j]] = [array[j], array[i]];
  }
}

shuffle(cards);


const gameBoard = document.getElementById("game-board");
const statusText = document.getElementById("status");

let firstCard = null;
let secondCard = null;
let lockBoard = false;
let matchedPairs = 0;

// Create cards on the board
cards.forEach((emoji, index) => {
  const card = document.createElement("div");
  card.classList.add("card");
  card.dataset.emoji = emoji;
  card.dataset.index = index;
  card.innerText = emoji;

  card.addEventListener("click", () => handleCardClick(card));
  gameBoard.appendChild(card);
});

function handleCardClick(card) {
  if (
    lockBoard ||
    card.classList.contains("matched") ||
    card.classList.contains("revealed")
  )
    return;

  card.classList.add("revealed");

  if (!firstCard) {
    firstCard = card;
  } else {
    secondCard = card;
    lockBoard = true;

    const isMatch = firstCard.dataset.emoji === secondCard.dataset.emoji;

    if (isMatch) {
      firstCard.classList.add("matched");
      secondCard.classList.add("matched");
      matchedPairs++;

      resetFlip();

      if (matchedPairs === emojis.length) {
        statusText.innerText = "🎉 You found all pairs!";
      }
    } else {
      setTimeout(() => {
        firstCard.classList.remove("revealed");
        secondCard.classList.remove("revealed");
        resetFlip();
      }, 1000);
    }
  }
}

function resetFlip() {
  [firstCard, secondCard] = [null, null];
  lockBoard = false;
}
