const url1 = "https://jsonplaceholder.typicode.com/todos";
const url2 = "https://jsonplaceholder.typicode.com/posts";
const url3 = "https://jsonplaceholder.typicode.com/users";

const getSomething = function (url) {
  return new Promise((resolve, reject) => {
    const request = new XMLHttpRequest();
    request.addEventListener("readystatechange", () => {
      if (request.readyState == 4 && request.status == 200) {
        resolve(JSON.parse(request.responseText));
      } else if (request.readyState == 4) {
        reject("could not fetch data");
      }
    });

    request.open("GET", url);
    request.send();
  });
};

getSomething(url1)
  .then((data) => {
    return getSomething(url2);
  })
  .then((data) => {
    console.log(data);
  })
  .catch((err) => {
    console.log("Error : " + err);
  });
