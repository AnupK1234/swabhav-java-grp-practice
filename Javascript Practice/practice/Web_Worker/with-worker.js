// main.js

// Create a new worker
const worker = new Worker("worker.js");

console.log("BEFORE WORKER");

// Send data to worker
worker.postMessage(6); // Calculate factorial of 5

// Receive message from worker
worker.onmessage = function (e) {
  console.log("Factorial is:", e.data);
};

// Handle errors (optional)
worker.onerror = function (error) {
  console.error("Worker error:", error.message);
};

console.log("AFTER WORKER");

