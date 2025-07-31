let secretNumber = Math.floor(Math.random() * 100) + 1;
let attempts = 5;
let guessedArray = [];

function checkGuess() {
  const guess = parseInt(document.getElementById("guessInput").value);
  const message = document.getElementById("message");
  const attemptsDisplay = document.getElementById("attemptsLeft");

  if (isNaN(guess) || guess < 1 || guess > 100) {
    message.textContent = "⛔ Please enter a number between 1 and 100!";
    return;
  }

  // check if guess is present
  if (!guessedArray.includes(guess)) {
    guessedArray.push(guess);
    attempts--;
  }

  console.log("Current secret Number is : " + secretNumber);

  attemptsDisplay.textContent = attempts;

  if (guess === secretNumber) {
    message.textContent = `✅ Correct! The number was ${secretNumber}`;
    endGame();
  } else if (attempts === 0) {
    message.textContent = `❌ Out of attempts! The number was ${secretNumber}`;
    endGame();
  } else if (
    guess == secretNumber - 1 ||
    guess == secretNumber - 2 ||
    guess == secretNumber - 3 ||
    guess == secretNumber + 1 ||
    guess == secretNumber + 2 ||
    guess == secretNumber + 3
  ) {
    message.textContent = "Almost there brooooo!";
  } else {
    message.textContent = guess < secretNumber ? "📉 Too low!" : "📈 Too high!";
  }
}

function endGame() {
  document.getElementById("guessInput").disabled = true;
}

function resetGame() {
  secretNumber = Math.floor(Math.random() * 100) + 1;
  attempts = 5;
  document.getElementById("attemptsLeft").textContent = attempts;
  document.getElementById("message").textContent = "";
  document.getElementById("guessInput").disabled = false;
  document.getElementById("guessInput").value = "";
}
