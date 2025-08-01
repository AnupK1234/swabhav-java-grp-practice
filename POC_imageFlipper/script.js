const board = document.getElementById('game-board');
const attemptCounter = document.getElementById('attempt-counter');
const resetBtn = document.getElementById('reset-btn');
const gameStatus = document.getElementById('game-status');

const totalPairs = 10;
let cards = [];
let cardone = null;
let cardtwo = null;
let lockBoard = false;
let attempts = 0;
let matchedPairs = 0;

// Utility to mixup an array
function mixup(array) {
  return array.sort(() => 0.5 - Math.random());
}

// Update attempt counter display
function updateAttemptCounter() {
  attemptCounter.innerText = `Attempts: ${attempts}`;
}

// Check if game is finished
function checkGameFinished() {
  if (matchedPairs === totalPairs) {
    gameStatus.innerText = "Game is finished!";
  }
}

// Create and initialize the board
function initBoard() {
  board.innerHTML = '';
  gameStatus.innerText = '';
  attempts = 0;
  matchedPairs = 0;
  cardone = null;
  cardtwo = null;
  lockBoard = false;
  updateAttemptCounter();

  cards = [];
  for (let i = 1; i <= totalPairs; i++) {
    cards.push(`images/img${i}.jpg`);
    cards.push(`images/img${i}.jpg`);
  }

  mixup(cards);

  cards.forEach((imgPath) => {
    const card = document.createElement('div');
    card.classList.add('card');

    card.innerHTML = `
      <div class="card-inner">
        <div class="card-front"></div>
        <div class="card-back" style="background-image: url('${imgPath}')"></div>
      </div>
    `;

    card.addEventListener('click', () => {
      if (lockBoard || card.classList.contains('flipped')) return;

      card.classList.add('flipped');

      if (!cardone) {
        cardone = card;
      } else {
        cardtwo = card;
        lockBoard = true;
        attempts++;
        updateAttemptCounter();

        const firstImg = cardone.querySelector('.card-back').style.backgroundImage;
        const secondImg = cardtwo.querySelector('.card-back').style.backgroundImage;

        if (firstImg === secondImg) {
          matchedPairs++;
          cardone = null;
          cardtwo = null;
          lockBoard = false;
          checkGameFinished();
        } else {
          setTimeout(() => {
            cardone.classList.remove('flipped');
            cardtwo.classList.remove('flipped');
            cardone = null;
            cardtwo = null;
            lockBoard = false;
          }, 1000);
        }
      }
    });

    board.appendChild(card);
  });
}

// Reset button event
resetBtn.addEventListener('click', () => {
  initBoard();
});

// Initialize game on page load
initBoard();
