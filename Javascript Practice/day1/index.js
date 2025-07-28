// console.log("Hello world");

let name = "Anup";

// console.log(typeof name);
// console.log(name.toUpperCase());

let email = "anup@gmail.com";

// console.log("The domain name of email: " + email.split("@")[1]);

let domain = email.substring(email.indexOf("@") + 1);

// console.log(domain.slice(-1, domain.indexOf('.')));
// console.log(domain);

// console.log(domain.split(".")[0]);

// let f_name = "Anup";
// let l_name = "Khismatrao";
// let full_name1 = f_name + " " + l_name;
// console.log(full_name1);

// let full_name2 = `${f_name} ${l_name}`;
// console.log(full_name2);

// Functions
function factorial(num) {
  let result = 1;
  for (let i = num; i >= 1; i--) {
    result = result * i;
  }
  return result;
}
// console.log(factorial(6));

const printName = function (firstName, lastName) {
  console.log(`Welcome ${firstName} ${lastName}!`);
};

// printName("Anup", "Khismatrao")

const addNumber = (num1, num2) => num1 + num2;

// console.log(addNumber(5,3));

const demoGreet = function (callback) {
  console.log("Hello world");
  callback(20);
};

const tempFunt = function (num) {
  console.log("Inside callback " + num);
};

demoGreet(tempFunt);

let html = ``;
let students = ["Anup", "Alex", "John"];

students.forEach((student, index) => {
  html += `<li>${student}</li>`;
});

const ulRef = document.querySelector("ol");
ulRef.innerHTML = html;

const arr = [
  {
    id: 1,
    f_name: "Anup",
    l_name: "Khismatrao",
  },
  {
    id: 2,
    f_name: "JOhn",
    l_name: "Doe",
  },
  {
    id: 3,
    f_name: "Alex",
    l_name: "Martin",
  },
  {
    id: 4,
    f_name: "Mark",
    l_name: "Sten",
  },
];

const tableRef = document.querySelector("tbody");
let tableHtml = ``;

arr.forEach((item) => {
  tableHtml += `
  <tr>
    <td>${item.id}</td>
    <td>${item.f_name}</td>
    <td>${item.l_name}</td>
  </tr>`;
});

tableRef.innerHTML = tableHtml;
