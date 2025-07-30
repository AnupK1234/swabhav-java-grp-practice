const numbers = [5, 10, 15, 20, 25, 30];
const users = [
  { name: "Alice", age: 25 },
  { name: "Bob", age: 17 },
  { name: "Charlie", age: 32 },
  { name: "David", age: 16 }
];

const doubled = numbers.map(num => num * 2);
console.log(doubled); 

const greaterThan15 = numbers.filter(num => num > 15);
console.log(greaterThan15); 

const sum = numbers.reduce((acc, num) => acc + num, 0);
console.log(sum); 


const userNamesWithA = users
  .filter(user => user.name.match(/A/i)) 
  .map(user => user.name);

console.log(userNamesWithA); 

const userNameStartWithA = users.filter(user=> user.name.startsWith('A')).map(user => user.name);
console.log(userNameStartWithA);

const totalAge = users.reduce((acc, user) => acc + user.age, 0);
console.log(totalAge); 


