        const circlesContainer = document.getElementById('circles-container');
        const attemptsLeftDisplay = document.getElementById('attempts-left');
        const messageDisplay = document.getElementById('message');
        const guessedNumbersList = document.getElementById('guessed-numbers-list');
        const resetButton = document.getElementById('reset-button');

        let randomNumber;
        let attemptsRemaining;
        const MAX_ATTEMPTS = 5;
        const MIN_NUMBER = 1;
        const MAX_NUMBER = 30;
        let guessedNumbers = [];
        let gameActive = false;

        function initializeGame() {
            randomNumber = Math.floor(Math.random() * (MAX_NUMBER - MIN_NUMBER + 1)) + MIN_NUMBER;
            attemptsRemaining = MAX_ATTEMPTS;
            guessedNumbers = [];
            gameActive = true;

            attemptsLeftDisplay.textContent = `Attempts left: ${attemptsRemaining}`;
            messageDisplay.textContent = '';
            guessedNumbersList.innerHTML = '';
            resetButton.classList.add('hidden');

            document.querySelectorAll('.circle').forEach(circle => {
                circle.classList.remove('bg-green-500', 'bg-red-500', 'bg-yellow-500', 'disabled');
                circle.classList.add('bg-gray-700', 'hover:bg-gray-600');
            });

            console.log("New game started. Number is:", randomNumber); 
        }

        function createCircles() {
            for (let i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
                const circle = document.createElement('div');
                circle.classList.add('circle', 'bg-gray-700', 'text-white');
                circle.textContent = i;
                circle.dataset.number = i; 

                circle.addEventListener('click', handleCircleClick);
                circlesContainer.appendChild(circle);
            }
        }

        function handleCircleClick(event) {
            if (!gameActive) {
                return; 
            }

            const clickedCircle = event.target;
            const userGuess = parseInt(clickedCircle.dataset.number);

            if (clickedCircle.classList.contains('disabled')) {
                return;
            }

            if (!guessedNumbers.includes(userGuess)) {
                guessedNumbers.push(userGuess);
                updateGuessedNumbersList();
            }

            attemptsRemaining--;
            attemptsLeftDisplay.textContent = `Attempts left: ${attemptsRemaining}`;

            clickedCircle.classList.remove('bg-gray-700', 'hover:bg-gray-600');
            clickedCircle.classList.add('disabled'); 

            if (userGuess === randomNumber) {
                messageDisplay.textContent = `Congratulations! You guessed the number ${randomNumber}!`;
                messageDisplay.className = 'mt-6 text-xl text-green-500'; 
                clickedCircle.classList.add('bg-green-500');
                endGame(true);
            } else if (attemptsRemaining === 0) {
                messageDisplay.textContent = `Game Over! The number was ${randomNumber}. Better luck next time!`;
                messageDisplay.className = 'mt-6 text-xl text-red-500'; 
                clickedCircle.classList.add('bg-red-500'); 
                endGame(false);
            } else if (userGuess < randomNumber) {
                messageDisplay.textContent = 'Too low! Try a higher number.';
                messageDisplay.className = 'mt-6 text-xl text-red-500'; 
                clickedCircle.classList.add('bg-red-500');
            } else { 
                messageDisplay.textContent = 'Too high! Try a lower number.';
                messageDisplay.className = 'mt-6 text-xl text-yellow-500'; 
                clickedCircle.classList.add('bg-yellow-500');
            }
        }
        function updateGuessedNumbersList() {
            guessedNumbersList.innerHTML = '';
            guessedNumbers.forEach(number => {
                const listItem = document.createElement('li');
                listItem.textContent = number;
                listItem.classList.add('inline-block', 'mx-1', 'bg-gray-700', 'py-1', 'px-3', 'rounded-full', 'text-sm');
                guessedNumbersList.appendChild(listItem);
            });
        }


        function endGame(won) {
            gameActive = false;
            document.querySelectorAll('.circle').forEach(circle => {
                circle.classList.add('disabled');
                circle.classList.remove('hover:bg-gray-600');
            });
            resetButton.classList.remove('hidden');
        }

        resetButton.addEventListener('click', initializeGame);

        document.addEventListener('DOMContentLoaded', () => {
            createCircles();
            initializeGame(); 
        });