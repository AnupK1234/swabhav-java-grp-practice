const { parentPort } = require('worker_threads');

// Listen for a message from the main thread (parentPort)
parentPort.on('message', () => {
  // Simulate a heavy calculation
  let sum = 0;
  for (let i = 0; i < 5000000000; i++) {
    sum += i;
  }

  // Send the result back to the main thread
  parentPort.postMessage(sum);
});