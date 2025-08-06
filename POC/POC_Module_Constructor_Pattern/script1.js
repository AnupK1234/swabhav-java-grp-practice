// The Constructor Pattern in JavaScript is used to create multiple instances of an object with the same structure and behavior.
// It's a common way to implement object-oriented programming in JavaScript using functions and the new keyword.
// A constructor function acts like a blueprint to create multiple objects.
//  When used with the new keyword, it initializes an object with given properties and methods.
// Constructor Function

// function Car(brand, model, year) {
//     this.brand = brand;
//     this.model = model;
//     this.year = year;

//     // Method defined inside the constructor (not recommended for memory efficiency)
//     this.startEngine = function () {
//         console.log(`${this.brand} ${this.model} engine started!`);
//     };
// }


// const car1 = new Car("Toyota", "Corolla", 2020);
// const car2 = new Car("Honda", "Civic", 2022);

// car1.startEngine(); 
// car2.startEngine(); 

// console.log(car1.startEngine===car2.startEngine);

// If you want to share methods across all instances (memory-efficient) , use prototype:
// Methods are not duplicated for each instance.
// Saves memory when creating many objects.
function Car(brand, model, year) {
    this.brand = brand;
    this.model = model;
    this.year = year;
}

Car.prototype.startEngine = function () {
    console.log(`${this.brand} ${this.model} engine started!`);
};

const car1 = new Car("Toyota", "Corolla", 2020);
const car2 = new Car("Honda", "Civic", 2022);

car1.startEngine();
car2.startEngine();

// console.log(car1.startEngine()==car2.startEngine());
console.log(car1.startEngine===car2.startEngine);