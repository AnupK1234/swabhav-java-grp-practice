const request = new XMLHttpRequest();

request.addEventListener("readystatechange", () => {
  console.log(request.response);
});

request.open("GET", "https://jsonplaceholder.typicode.com/todos");

request.send();
