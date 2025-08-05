// worker.js

// Listen for messages from main thread
onmessage = function (e) {
  const number = e.data;
  console.log("Worker received:", number);

  // Do heavy calculation (e.g., finding factorial)
  let result = 1;
  for (let i = 1; i <= number; i++) {
    result *= i;
  }


  let sum = 0;
    for (let i = 0; i < 10000000000; i++) {
      sum += i;
    }

  // Send result back to main thread
  postMessage(result);
};
