// Immediately Invoked Function Expressions (IIFE)

// // Function Declaration
// function hello() {
//   console.log("Hello!");
// }

// // Function Expression (anonymous)
// const myFunction = function() {
//   console.log("Hello from a function expression!");
// };

// // Function Expression
// (function() {
//   console.log("This is now a function expression.");
// });

// (function() {
//   console.log("Hello from an IIFE!");
// })();


// // A variable in the global scope
// let globalVariable = "I am global!";

// // --- File A ---
// (function() {
//   let fileAVariable = "I am from File A.";
//   console.log(globalVariable); // Can access global variable
//   console.log(fileAVariable);  // Can access its own variable
// })();

// // --- File B ---
// (function() {
//   let fileBVariable = "I am from File B.";
//   // console.log(fileAVariable); 
//   console.log(fileBVariable);
// })();

// // console.log(fileAVariable); // ReferenceError


// 2. Creating Data Privacy with the Module Pattern

const counterModule = (function() {
  // This is a private variable, it's hidden from the outside.
  let counter = 0;

  // This is a private function.
  function increment() {
    counter++;
  }

  // We expose public methods by returning an object.
  return {
    getCounter: function() {
      return counter;
    },
    incrementCounter: function() {
      increment(); // We can call the private function from here.
    }
  };
})();

// Now we can use our module
console.log(counterModule.getCounter()); // 0
counterModule.incrementCounter();
console.log(counterModule.getCounter()); // 1

// We can't access the private counter directly
// console.log(counterModule.counter); // undefined
// console.log(counter);             // ReferenceError
