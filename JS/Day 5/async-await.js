const getData = async (URL) => {
  const response = await fetch(URL);
  if (response.status != 200) {
    throw new Error("Could not fetch data");
  }
  const data = await response.json();
  return data;
};

const URL1 = "https://jsonplaceholder.typicode.com/todos/1";
const URL2 = "https://jsonplaceholder.typicode.com/posts/1";
const URL3 = "https://jsonplaceholder.typicode.com/users/1";

getData(URL1)
  .then((data) => {
    console.log(data);
    return getData(URL2);
  })
  .then((data) => {
    console.log(data);
    return getData(URL3);
  })
  .then((data) => {
    console.log(data);
  })
  .catch((err) => {
    console.log(err.message);
  });
