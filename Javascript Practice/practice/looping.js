const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

// Step 1: Filter - Get only even numbers
const evenNumbers = numbers.filter(num => num % 2 === 0);
// Result: [2, 4, 6, 8, 10]

// Step 2: Map - Square each even number
const squaredEvenNumbers = evenNumbers.map(num => num * num);
// Result: [4, 16, 36, 64, 100]

// Step 3: Reduce - Sum all squared even numbers
const sumOfSquares = squaredEvenNumbers.reduce((acc, num) => acc + num, 0);
// Result: 220

// Output the results
console.log("Original Numbers: ", numbers);
console.log("Even Numbers: ", evenNumbers);
console.log("Squared Even Numbers: ", squaredEvenNumbers);
console.log("Sum of Squared Even Numbers: ", sumOfSquares);
