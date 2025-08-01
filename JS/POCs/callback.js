// --- Callbacks PoC ---

function fetchUserDataWithCallback(userId, callback) {
    console.log(`[Callbacks] Attempting to fetch user data for ID: ${userId}...`);
    const delay = Math.random() * 1000 + 500; // Simulate network delay (0.5s to 1.5s)

    setTimeout(() => {
        if (userId === 123) {
            const userData = { id: userId, name: "Alice Wonderland (Callback)" };
            console.log(`[Callbacks] Data fetched successfully for ID: ${userId}`);
            callback(null, userData); // Convention: first argument is error, second is data
        } else {
            const error = new Error(`User with ID ${userId} not found (Callback)`);
            console.error(`[Callbacks] Error fetching data for ID: ${userId}`);
            callback(error, null);
        }
    }, delay);
}

// --- Usage of Callbacks ---

console.log("--- Starting Callbacks PoC ---");

// Successful case
fetchUserDataWithCallback(123, (error, user) => {
    if (error) {
        console.error(`[Callbacks] Error in success case: ${error.message}`);
    } else {
        console.log(`[Callbacks] Received user (success): ${user.name}`);

        // Example of callback hell: trying to fetch user's posts after fetching user
        fetchUserPostsWithCallback(user.id, (postError, posts) => {
            if (postError) {
                console.error(`[Callbacks] Error fetching posts: ${postError.message}`);
            } else {
                console.log(`[Callbacks] Received posts for ${user.name}:`, posts);
            }
        });
    }
});

// Failed case
fetchUserDataWithCallback(456, (error, user) => {
    if (error) {
        console.error(`[Callbacks] Error in failure case: ${error.message}`);
    } else {
        console.log(`[Callbacks] Received user (failure should not happen): ${user.name}`);
    }
});

// Helper for callback hell demonstration
function fetchUserPostsWithCallback(userId, callback) {
    console.log(`[Callbacks] Attempting to fetch posts for user ID: ${userId}...`);
    const delay = Math.random() * 800 + 300; // Simulate network delay

    setTimeout(() => {
        if (userId === 123) {
            const posts = [
                { id: 1, title: "My First Post" },
                { id: 2, title: "Exploring Callbacks" }
            ];
            callback(null, posts);
        } else {
            callback(new Error(`No posts found for user ID ${userId}`), null);
        }
    }, delay);
}

console.log("--- Callbacks PoC initiated, waiting for results ---");