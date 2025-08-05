// Check if a number is prime
function isPrime(num) {
  if (num < 2) return false;
  for (let i = 2; i * i <= num; i++) {
    if (num % i === 0) return false;
  }
  return true;
}

// Function that blocks the main thread
function findPrimes(max) {
  const primes = [];
  for (let i = 2; i <= max; i++) {
    if (isPrime(i)) {
      primes.push(i);
    }
  }
  return primes;
}

document.getElementById('start-button').addEventListener('click', () => {
  const maxNumber = 10000000;
  document.getElementById('result').textContent = 'Finding primes...';
  console.log('Main: Starting heavy calculation on main thread');

  // Blocking operation!
  const primes = findPrimes(maxNumber);

  document.getElementById('result').textContent = `Found ${primes.length} prime numbers!`;
  console.log('Main: Finished calculation');
});
