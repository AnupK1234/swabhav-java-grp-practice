class CounterModule {
  #counter = 0; // Private field using ES6 syntax

  getCounter() {
    return this.#counter;
  }

  incrementCounter() {
    this.#counter++;
  }
}

const counterModule = new CounterModule();
console.log(counterModule.getCounter()); // 0
counterModule.incrementCounter();
console.log(counterModule.getCounter()); // 1

// Trying to access private field directly
// console.log(counterModule.#counter); 
