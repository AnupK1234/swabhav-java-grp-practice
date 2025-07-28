async function callAPI() {
    const data = await fetch("https://randomuser.me/api/");
    const data2 = await data.json();
    // console.log(data2.results[0]);
    
    greetUser(data2.results[0]);
}

function greetUser(userData) {
    console.log("Welcome " + userData.name.first);
}

callAPI();


// callback hell - akshay saini  namaste js async await