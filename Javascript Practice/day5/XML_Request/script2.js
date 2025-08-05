const url1 = "https://jsonplaceholder.typicode.com/todos";
const url2 = "https://jsonplaceholder.typicode.com/posts";
const url3 = "https://jsonplaceholder.typicode.com/users";

const getRequest = function (url, callback) {
  const request = new XMLHttpRequest();
  request.addEventListener("readystatechange", () => {
    if (request.readyState == 4 && request.status == 200) {
      callback(undefined, JSON.parse(request.responseText));
    } else if (request.readyState == 4) {
      callback("could not fetch data", undefined);
    }
  });

  request.open("GET", url);
  request.send();
};

// CALLBACK HELL!!!
getRequest(url1, (err, data) => {
  if (!err) {
    getRequest(url2, (err, data) => {
      if (!err) {
        getRequest(url3, (err, data) => {
          if (!err) {
          } else {
            console.log("Error occured : " + err);
          }
        });
      } else {
        console.log("Error occured : " + err);
      }
    });
  } else {
    console.log("Error occured : " + err);
  }
});
