document.getElementById('start-button').addEventListener('click', () => {
  if (window.Worker) {
    // Create a new web worker
    
    const worker = new Worker('worker.js');

    // Start the worker with the range (e.g., find primes up to 100000)
    const maxNumber = 1000000000;
    document.getElementById('result').textContent = 'Finding primes...';
    console.log('Main: Sending number to worker');

    worker.postMessage(maxNumber);

    // Receive result from the worker
    worker.onmessage = function(event) {
      const primes = event.data;
      document.getElementById('result').textContent = `Found ${primes.length} prime numbers!`;
      console.log('Main: Received result from worker');

      // Stop the worker
      worker.terminate();
    };
  } else {
    document.getElementById('result').textContent = 'Web Workers not supported!';
  }
});
