function callAPI(callback) {
  fetch("https://randomuser.me/api/")
    .then(res => res.json())
    .then(data => {
      // Pass the user data to the callback
      callback(data.results[0]);
    })
    .catch(err => console.error("Error:", err));
}

function greetUser(userData) {
  console.log("Welcome " + userData.name.first);
}

// Pass greetUser as a callback
callAPI(greetUser);


// async function callAPI() {
//     const data = await fetch("https://randomuser.me/api/");
//     const data2 = await data.json();
//     // console.log(data2.results[0]);
    
//     greetUser(data2.results[0]);
// }

// function greetUser(userData) {
//     console.log("Welcome " + userData.name.first);
// }

// callAPI();


// callback hell - akshay saini  namaste js async await