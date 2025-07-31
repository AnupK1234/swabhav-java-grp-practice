// Task 1
// let pRef = document.querySelector("p");
// console.log(pRef.innerText)


// Task 2
let pRef = document.querySelectorAll("p");
pRef.forEach((item) => {
  if (item.className === "test") {
    // console.log(item.innerText);
  }
});

// console.log(Array.from(pRef));

function changeHref() {
    let aRef = document.getElementById("myanchor");
    aRef.setAttribute("href", "https://www.swabhav.ai");
}

function displayText() {
  let inputRef = document.getElementById("text-box");
  let pRef = document.getElementById("display-text");
  
  if(inputRef.value === "") {
    alert("Please enter some text")
    return;
  }
  
  pRef.innerText = inputRef.value;
}


let inputRef = document.getElementById("text-box2");

inputRef.addEventListener("input", () => {
  let pRef = document.getElementById("display-text2");
  pRef.innerText = inputRef.value;
});


let imgRef = document.getElementById("my-img");
// setTimeout(() => {
//     imgRef.setAttribute("src", "https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/body-image/public/rolls-rocyce-cullinan-top_10.jpg")
// }, 3000);

let count = 0;
setInterval(() => {
    let first = "https://media.architecturaldigest.com/photos/66a914f1a958d12e0cc94a8e/16:9/w_2560%2Cc_limit/DSC_5903.jpg";
    
    let second = "https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/body-image/public/rolls-rocyce-cullinan-top_10.jpg";
    
    if(count%2 == 0) imgRef.setAttribute("src", first);
    else imgRef.setAttribute("src", second);
    count++;
}, 3000);


setInterval(() => {
    let date = new Date();
    let timeRef = document.getElementById("show-time");
    timeRef.innerText = `${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`
}, 1000);


function addClass() {
  let element = document.getElementById("ele1");
  element.classList.add("success");
  element.classList.toggle("error");
}