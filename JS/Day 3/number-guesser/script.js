const guessInput = document.getElementById('guess-input');
const guessButton = document.getElementById('guess-button');
const attemptsLeftDisplay = document.getElementById('attempts-left');
const messageDisplay = document.getElementById('message');
const guessedNumbersList = document.getElementById('guessed-numbers-list');
const resetButton = document.getElementById('reset-button');

let randomNumber;
let attemptsRemaining;
const MAX_ATTEMPTS = 5;
let guessedNumbers = [];

function initialize() {
    randomNumber = Math.floor(Math.random() * 100) + 1;
    attemptsRemaining = MAX_ATTEMPTS;
    guessedNumbers = [];

    attemptsLeftDisplay.textContent = `Attempts left: ${attemptsRemaining}`;
    messageDisplay.textContent = '';
    guessedNumbersList.innerHTML = ''; 
    guessInput.value = '';
    guessInput.disabled = false;
    guessButton.disabled = false;
    resetButton.classList.add('hidden');
    console.log("New game started. Number is:", randomNumber);
}

function checkGuess() {
    const userGuess = parseInt(guessInput.value);
    // const userGuess = guessInput.value;
    console.log(typeof userGuess);

    if (isNaN(userGuess) || userGuess < 1 || userGuess > 100) {
        messageDisplay.textContent = 'Please enter a valid number between 1 and 100.';
        messageDisplay.className = 'mt-6 text-xl text-yellow-400'; 
        return;
    }

    if (guessedNumbers.includes(userGuess)) {
        messageDisplay.textContent = 'You already guessed that number!';
        messageDisplay.className = 'mt-6 text-xl text-yellow-400';
        return;
    }

    guessedNumbers.push(userGuess);
    updateGuessedNumbersList();

    attemptsRemaining--;
    attemptsLeftDisplay.textContent = `Attempts left: ${attemptsRemaining}`;

    if (userGuess === randomNumber) {
        messageDisplay.textContent = `Congratulations! You guessed the number ${randomNumber}!`;
        messageDisplay.className = 'mt-6 text-xl text-green-500'; 
        gameEnded(true);
    } else if (attemptsRemaining === 0) {
        messageDisplay.textContent = `Game Over! You are going to Noida Broo.. The number was ${randomNumber}.`;
        messageDisplay.className = 'mt-6 text-xl text-red-500'; 
        gameEnded(false);
    } else if (userGuess < randomNumber) {
        messageDisplay.textContent = 'Too low! Try a higher number.';
        messageDisplay.className = 'mt-6 text-xl text-blue-400'; 
    } else {
        messageDisplay.textContent = 'Too high! Try a lower number.';
        messageDisplay.className = 'mt-6 text-xl text-blue-400';
    }

    guessInput.value = ''; 
}

function updateGuessedNumbersList() {
    guessedNumbersList.innerHTML = ''; 
    guessedNumbers.forEach(number => {
        const listItem = document.createElement('li');
        listItem.textContent = number;
        listItem.classList.add('inline-block', 'mx-2', 'bg-gray-700', 'py-1', 'px-3', 'rounded');
        guessedNumbersList.appendChild(listItem);
    });
}

function gameEnded(won) {
    guessInput.disabled = true;
    guessButton.disabled = true;
    resetButton.classList.remove('hidden'); 
}

guessButton.addEventListener('click', checkGuess);
guessInput.addEventListener('keypress', function(event) {
    if (event.key === 'Enter') {
        checkGuess();
    }
});
resetButton.addEventListener('click', initialize);

initialize();