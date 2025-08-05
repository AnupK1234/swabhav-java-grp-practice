// Prime number checking function
function isPrime(num) {
  if (num < 2) {
    return false;
}
  for (let i = 2; i * i <= num; i++) {
    if (num % i === 0) return false;
  }
  return true;
}

// Message received from main thread
self.onmessage = function(event) {
  const max = event.data;
  console.log('Worker: Starting prime search up to', max);

  const primes = [];

  for (let i = 2; i <= max; i++) {
    if (isPrime(i)) {
      primes.push(i);
    }
  }

  // Send result back to main thread
  self.postMessage(primes);
  console.log('Worker: Finished sending primes');
};
