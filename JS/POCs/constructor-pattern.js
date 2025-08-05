function Person(name, age) {
  this.name = name;
  this.age = age;
  this.sayHello = function() {
    console.log(`Hello, my name is ${this.name} and I am ${this.age} years old.`);
  };
}

// Create new objects using the constructor
var person1 = new Person('Alice', 30);
var person2 = new Person('Bob', 25);

person1.sayHello(); // "Hello, my name is Alice and I am 30 years old."
person2.sayHello(); // "Hello, my name is Bob and I am 25 years old."