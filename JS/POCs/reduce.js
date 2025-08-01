const numbers = [5, 10, 15, 20, 25, 30];
const users = [
  { name: "Alice", age: 25 },
  { name: "Bob", age: 17 },
  { name: "Charlie", age: 32 },
  { name: "David", age: 16 }
];

const sum = numbers.reduce((acc, num) => acc + num, 0);
console.log(sum); 

const totalAge = users.reduce((acc, user) => acc + user.age, 0);
console.log(totalAge); 