// --- Promises PoC ---

function fetchUserDataWithPromise(userId) {
    console.log(`\n[Promises] Attempting to fetch user data for ID: ${userId}...`);
    const delay = Math.random() * 1000 + 500; // Simulate network delay (0.5s to 1.5s)

    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (userId === 123) {
                const userData = { id: userId, name: "Bob Builder (Promise)" };
                console.log(`[Promises] Data fetched successfully for ID: ${userId}`);
                resolve(userData);
            } else {
                const error = new Error(`User with ID ${userId} not found (Promise)`);
                console.error(`[Promises] Error fetching data for ID: ${userId}`);
                reject(error);
            }
        }, delay);
    });
}

// Helper for chaining demonstration
function fetchUserPostsWithPromise(userId) {
    console.log(`[Promises] Attempting to fetch posts for user ID: ${userId}...`);
    const delay = Math.random() * 800 + 300; // Simulate network delay

    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (userId === 123) {
                const posts = [
                    { id: 101, title: "My Promise Journey" },
                    { id: 102, title: "Asynchronous Awesomeness" }
                ];
                resolve(posts);
            } else {
                reject(new Error(`No posts found for user ID ${userId} (Promise)`));
            }
        }, delay);
    });
}


// --- Usage of Promises ---

console.log("--- Starting Promises PoC ---");

// Successful case and chaining
fetchUserDataWithPromise(123)
    .then(user => {
        console.log(`[Promises] Received user (success): ${user.name}`);
        // Chain another async operation: fetch posts for this user
        return fetchUserPostsWithPromise(user.id); // Return the promise to continue the chain
    })
    .then(posts => {
        console.log(`[Promises] Received posts:`, posts);
    })
    .catch(error => {
        // This catch handles any error in the entire chain above it
        console.error(`[Promises] An error occurred in the chain: ${error.message}`);
    });

// Failed case
fetchUserDataWithPromise(456)
    .then(user => {
        console.log(`[Promises] Received user (failure should not happen): ${user.name}`);
    })
    .catch(error => {
        // This catch specifically handles the error from fetchUserDataWithPromise(456)
        console.error(`[Promises] Error in failed case: ${error.message}`);
    });


// Example of Promise.all - fetching multiple pieces of data concurrently
console.log("\n[Promises] Demonstrating Promise.all for concurrent fetches...");
Promise.all([
    fetchUserDataWithPromise(123),
    new Promise(resolve => setTimeout(() => resolve({ message: "Auxiliary data fetched!" }), 700)) // Another dummy promise
])
.then(results => {
    console.log("[Promises] All concurrent fetches completed successfully:", results);
})
.catch(error => {
    console.error("[Promises] One of the concurrent fetches failed:", error.message);
});


console.log("--- Promises PoC initiated, waiting for results ---");