fetch("https://jsonplaceholder.typicode.com/todos").then((response) => {
    if(response.status == 200) {
        return response.json();
    }
    throw new Error("Could not fetch data");
}).then((data) => {
    console.log(data);
}).catch((err) => {
    console.log(err);
    
})