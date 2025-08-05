const getRequest = (URL) => {
  return new Promise((resolve, reject) => {
    const request = new XMLHttpRequest();

    request.addEventListener("readystatechange", () => {
      if (request.readyState == 4 && request.status == 200) {
        resolve(JSON.parse(request.responseText));
      } else if (request.readyState == 4) {
        reject("could not fetch data");
      }
    });

    request.open("GET", URL);
    request.send();
  });
};

const URL1 = "https://jsonplaceholder.typicode.com/todos/1";
const URL2 = "https://jsonplaceholder.typicode.com/posts/1";
const URL3 = "https://jsonplaceholder.typicode.com/users/1";

getRequest(URL1)
  .then((data) => {
    console.log(data);
    return getRequest(URL2);
  })
  .then((data) => {
    console.log(data);
    return getRequest(URL3);
  })
  .then((data) => {
    console.log(data);
  })
  .catch((err) => {
    console.log(err);
  });
