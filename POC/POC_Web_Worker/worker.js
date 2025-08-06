// Background thread: does heavy work here
let sum = 0;
self.onmessage = function (e) {
  if (e.data === 'start') {
    

    // Sum all numbers from 1 to 1e9
    for (let i = 1; i <= 1000000000; i++) {
      sum += i;
    }

    // Send result back to main thread
    self.postMessage(sum);
  }
};

// Logs 'DedicatedWorkerGlobalScope' — which is like 'window' in workers
console.log(self);

/*
  worker.postMessage(data) → sends data to worker.
  worker.onmessage → receives message from worker.
  self → refers to the worker's global scope (like window in main thread).

  Benefits:
  - Keeps UI smooth and responsive
  - Utilizes multi-threading in JavaScript

  Note:
  - It behaves like 'window' in the main thread but in the background thread.
  - Since workers don’t have access to window or document, 'self' is how you interact with the worker’s environment.
*/
