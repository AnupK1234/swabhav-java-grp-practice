// const CartModule = (function () {
//   let cart = [];

//   function addItem(item) {
//     cart.push(item);
//   }

//   function getTotal() {
//     return cart.reduce((sum, item) => sum + item.price, 0);
//   }

//   return {
//     addToCart: addItem,
//     calculateTotal: getTotal,
//   };
// })();


// CartModule.addToCart({ name: 'Apple', price: 30 });
// CartModule.addToCart({ name: 'Banana', price: 20 });

// const total = CartModule.calculateTotal();
// console.log(total);

// function Person(name, age) {
//   this.name = name;
//   this.age = age;

//   this.greet = function () {
//     console.log(`Hi, I'm ${this.name} and I'm ${this.age} years old.`);
//   };
// }
// const p1 = new Person("Alice", 25);
// const p2 = new Person("Bob", 30);


// function Person(name, age) {
//   this.name = name;
//   this.age = age;

//   Person.prototype.greet = function () {
//     console.log(`Hi, I'm ${this.name} and I'm ${this.age} years old.`);
//   };
// }
// const p1 = new Person("Alice", 25);
// const p2 = new Person("Bob", 30);

// p1.greet();
// p2.greet();
// console.log(p1.greet===p2.greet);




