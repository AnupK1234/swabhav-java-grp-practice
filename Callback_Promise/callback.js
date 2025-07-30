// Callback function to handle joke
function displayJoke(joke) {
    console.log("Here's a joke:");
    console.log(joke.setup);
    console.log(joke.punchline);
}

// Function to fetch joke using a callback
function fetchJoke(callback) {
    fetch('https://official-joke-api.appspot.com/jokes/random')
        .then(response => response.json())
        .then(data => {
            callback(data); // Pass joke to callback
        })
        .catch(error => {
            console.error("Failed to fetch joke:", error);
        });
}

// Fetch a joke and display it
fetchJoke(displayJoke);
