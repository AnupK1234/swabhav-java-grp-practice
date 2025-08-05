const url = "https://jsonplaceholder.typicode.com/todos";

fetch(url)
  .then((data) => {
    return data.json();
  })
  .then((data) => {
    console.log(data);
  })
  .catch((err) => {
    console.log(err);
  });
  
