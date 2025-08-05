const url = "https://jsonplaceholder.typicode.com/todos";

const getData = async function () {
  const response = await fetch(url);
  console.log(response);
};


