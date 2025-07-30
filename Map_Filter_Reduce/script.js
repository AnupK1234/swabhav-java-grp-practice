const users = [
  { id: 1, name: 'Alice', age: 22 },
  { id: 2, name: 'Bob', age: 27 },
  { id: 3, name: 'Charlie', age: 17 },
  { id: 4, name: 'David', age: 30 }
];
// 1. Map
const anything= users.map((user,index)=>{
  console.log("Value: "+(user.age+3)+ "\nIndex: "+index);
});

//Returns a new array where each item is modified.
const names = users.map(user => user.name);
console.log(names); 


const updatedUsers = users.map(user => ({
  ...user, 
  isAdult: user.age >= 18
}));
console.log(updatedUsers);


// 2. Filter
//Returns a new array with only the items that pass a condition.
const adults = users.filter(user => user.age >= 20);
console.log(adults); 

const startsWithA = users.filter(user => user.name.startsWith('B'));
console.log(startsWithA);


// 3. Reduce
//Returns a single value by reducing the array.
const totalAge = users.reduce((sum, user) => sum + user.age, 0);
console.log(totalAge); 

const oldest = users.reduce((max, user) => (user.age > max.age ? user : max));
console.log(oldest); 


