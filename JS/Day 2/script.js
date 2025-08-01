let employee = {
  id: 101,
  firstName: "Nilesh",
  lastName: "Gawli",
  skills: ["C", "C++", "C+++"],

  login: function () {
    console.log("employee logged in");
  },
  logout: function () {
    console.log("employee logged out");
  },
  getAl1Skills: function () {
    this.skills.forEach((skill) => {
      console.log(skill);
    });
  },
};

// employee.login();
// employee.getAl1Skills();

// const para = document.querySelector('p');
// console.log(para.innerText);

// const paragraphs = document.querySelectorAll('p');

// paragraphs.forEach((p, index) => {
//   console.log(`Paragraph ${index + 1}: ${p.innerText}`);
// });

// const testParagraphs = document.querySelectorAll('p.test');

// testParagraphs.forEach((p, index) => {
//   console.log(`Paragraph ${index + 1}: ${p.innerText}`);
// });

// const a = document.querySelector('a');
// const btnRef = document.querySelector('button');

// btnRef.addEventListener("click", changeURL());

// function changeURL()
//  {
//     a.setAttribute("href", "https://swabhavtechlabs.com/");
//  }
// console.log(a.getAttribute)

const inputRef = document.querySelector("input");
// const btnRef = document.querySelector("button");
const paraRef = document.querySelector("#p");

// btnRef.addEventListener("click", () => {
//   if (inputRef.value != "") {
//     paraRef.innerHTML = inputRef.value;
//     inputRef.value = "";
//   }
//   else {
//     alert("Enter some text")
//   }
// });

// inputRef.addEventListener("input", () => {
//     paraRef.innerHTML = inputRef.value;
// })


// const imgRef = document.querySelector("img");
// let toggle = true;


// setInterval(() => {
//     if(toggle) {
//           document.querySelector("img").src =
//     "https://i.ytimg.com/vi/UnVOMPmq63g/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLCh41RljNEEL6JBYh4hDZ5GxWjNmQ";
//     }
//     else {
//         document.querySelector("img").src =
//     "https://i.ytimg.com/vi/a74jHiJvJs0/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLA2VejoRKaPIgaU9QiHo9bNF1SBKw";
//     }

//     toggle = !toggle;

// }, 1500); 

function startTime() {
  const today = new Date();
  let h = today.getHours();
  let m = today.getMinutes();
  let s = today.getSeconds();
  m = checkTime(m);
  s = checkTime(s);
  s = checkAM(h,s);

  document.getElementById('clock').innerHTML =  h + ":" + m + ":" + s;
  setTimeout(startTime, 1000);
}

function checkTime(i) {
  if (i < 10) {i = "0" + i};  
  return i;
}

function checkAM(h, s) {
    return h>12 ? s = s + " PM" : s = s + " AM";
}


startTime();