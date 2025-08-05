const request = new XMLHttpRequest();

//check status of request (readystatechange)

request.addEventListener("readystatechange", () => {

    if(request.readyState == 4 && request.status == 200) {
        console.log(request.responseText);
    }
    else if(request.readyState == 4) {
        console.log("Could not fetch data");
    }
});


request.open("GET", "https://jsonplaceholder.typicode.com/todos")
request.send();