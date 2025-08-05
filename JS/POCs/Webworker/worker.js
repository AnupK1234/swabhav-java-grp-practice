// The 'self' keyword refers to the worker's global scope.
self.onmessage = function(event) {
    const data = event.data;
    console.log('Worker: Received message from main thread:', data);

    // Simulate a heavy, blocking task with a long loop
    let sum = 0;
    for (let i = 0; i < 5000000000; i++) {
        sum += i;
    }

    const result = `Calculation complete! The sum is: ${sum}`;
    console.log('Worker: Sending result to main thread.');
    
    // Post the result back to the main thread
    self.postMessage(result);
};