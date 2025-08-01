// alert("hlelo");

// console.log("hello world")

// let n = 10;
// console.log(typeof n);

// n = "Nilesh";
// console.log(typeof n);

// n = true;
// console.log(typeof n);

//String

// let fullname = "Nilesh Gawli";
// console.log(fullname.charAt(0));
// console.log(fullname.concat(" Sir"));
// console.log(fullname.toLowerCase());

// let result = fullname.split(" ");
// console.log(result);

// let email = "nbgawli.aurionpro@gmail.com";
// let domain = email.split("@")[1];  
// console.log(domain); 

// domain = email.substring(email.indexOf("@") +1);
// console.log(domain);

// r = email.substring(email.indexOf("@")+1, email.indexOf("."));
// console.log(r);

// let author = "Ben Stokes";
// let blog = "My journey to glory";
// let likes = 500;

// let result = "Blog: " + blog + " by " + author + " has " + likes + " likes.";
// console.log(result);

// result = `Blog: ${blog} by ${author} has ${likes} likes.`;
// console.log(result);

// let players = ["Rohit", "Dhawan", "Virat",];

// let value1 = null;
// console.log(value1);
// console.log(value1 + 10);
// console.log(value1 + " Hello");

// let value2;
// console.log(value2);
// console.log(value2 + " Hello");
// console.log(value2 + 10);

// let age = 20;
// let age2 = "20";

// console.log(age == age2);
// console.log(age === age2);

// console.log(null === 0);

// let v1 = NaN;
// let v2 = NaN;
// console.log(v1==v2);

// function greet(name) {
//     console.log(name);
// }

// greet("nilesh");

// function factorial(n) {
//   if (n < 0) return undefined;
//   if (n === 0 || n === 1) return 1;
//   return n * factorial(n - 1);
// }

// console.log(factorial(5));

// let result = function factorialIterative(n) {
//   if (n < 0) return undefined;
//   let result = 1;
//   for (let i = 2; i <= n; i++) {
//     result *= i;
//   }
//   return result;
// }

// console.log(result(5));

// function expression

// const greet = function(firstname, lastname = "Gawli") {
//     console.log(`Welcome back ${firstname} ${lastname}`);
// };

// greet("Nilesh");

// arrow function

// const addNumber = (firstNumber, secondNumber) => firstNumber + secondNumber;
// console.log(addNumber(10, 20));


// const getName = function(name) {
//     console.log(`Hello ${name}`);
// }


// const demoGreet = function(callback) {
//     console.log("inside demogreet");
//     callback("Nilesh");
// }

// demoGreet(getName);

// let items = ["Milk", "Bread"];

// const ulRef = document.querySelector("ol");
// console.log(ulRef);
// itemList = ``;

// items.forEach((item) => {
//     itemList += `<li>${item} x 2</li>`;
// });

// console.log(itemList);
// ulRef.innerHTML = itemList;

const students = [
    {
        id: 101,
        firstName: "Nilesh",
        lastName: "Gawli",
        email: "nbgawli@gmail.com"
    },
        {
        id: 102,
        firstName: "Rohan",
        lastName: "Maurya",
        email: "rohan@gmail.com"
    },
        {
        id: 103,
        firstName: "Rohit",
        lastName: "Sharma",
        email: "vaha@gmail.com"
    },
        {
        id: 104,
        firstName: "Shikhar",
        lastName: "Dhawan",
        email: "sd@gmail.com"
    },
];
const tbodyRef = document.querySelector("tbody");

let html = ``;
students.forEach(student => {
    html += `
        <tr>
        <td> ${student.id} </td>
        <td> ${student.firstName} </td>
        <td> ${student.lastName} </td>
        <td> ${student.email} </td>
        </td>
    `;
});
console.log(html)
tbodyRef.innerHTML = html;