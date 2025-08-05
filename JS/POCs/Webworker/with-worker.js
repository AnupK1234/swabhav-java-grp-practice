document.getElementById('calculate-button').addEventListener('click', () => {
    // Check if the browser supports Web Workers
    if (window.Worker) {
        // Create a new web worker
        const myWorker = new Worker('worker.js');

        // Send a message to the worker to start the calculation
        document.getElementById('result').textContent = 'Calculating...';
        console.log('Main: Sending message to worker.');
        myWorker.postMessage('Start calculation');

        // Listen for a message from the worker
        myWorker.onmessage = function(event) {
            console.log('Main: Received message from worker:', event.data);
            document.getElementById('result').textContent = event.data;
            // Terminate the worker after we get the result
            myWorker.terminate();
        };

    } else {
        // Fallback for browsers that don't support Web Workers
        document.getElementById('result').textContent = 'Web Workers are not supported in this browser.';
        console.log('Your browser does not support Web Workers.');
    }
});