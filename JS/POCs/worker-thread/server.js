// server.js
const { Worker } = require('worker_threads');
const http = require('http');

http.createServer((req, res) => {
    // Set headers to allow the browser to make requests from a different origin (CORS)
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST');

    if (req.url === '/heavy-calculation') {
        console.log('Server: Received request for heavy calculation. Spawning worker thread.');

        // Create a new worker thread
        const worker = new Worker('./worker.js');

        // Listen for the result from the worker
        worker.on('message', (result) => {
            console.log('Server: Received result from worker. Sending to client.');
            res.end(`Result: ${result}`);
            worker.terminate(); // Good practice to terminate after use
        });

        // Handle any errors
        worker.on('error', (err) => {
            console.error('Server: Worker error!', err);
            res.end(`Error: ${err.message}`);
        });

        // Send a message to the worker to start the calculation (optional, but good practice for clarity)
        worker.postMessage('start');

    } else {
        // This part of the code can still run while the worker is busy
        res.end('Hello! This is a responsive server.');
    }
}).listen(3000, () => {
    console.log('Server is running on http://localhost:3000');
});