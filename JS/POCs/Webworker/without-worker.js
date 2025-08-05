// main-blocking.js

// Function to perform the heavy calculation
function performHeavyCalculation() {
    // This is the same heavy loop as in the web worker
    // but now it's running on the main thread.
    let sum = 0;
    for (let i = 0; i < 5000000000; i++) {
        sum += i;
    }
    return `Calculation complete! The sum is: ${sum}`;
}

document.getElementById('calculate-button').addEventListener('click', () => {
    // Update the UI to show the calculation has started
    document.getElementById('result').textContent = 'Calculating...';
    console.log('Main: Starting heavy calculation on the main thread.');

    // This is the blocking call. The entire browser tab will freeze here
    // until the loop completes.
    const result = performHeavyCalculation();

    // The UI will only be updated AFTER the long-running task is finished.
    document.getElementById('result').textContent = result;
    console.log('Main: Calculation finished and UI updated.');
});