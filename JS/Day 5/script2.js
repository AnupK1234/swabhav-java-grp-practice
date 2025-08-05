const getRequest = (URL, callback) => {
  const request = new XMLHttpRequest();

  request.addEventListener("readystatechange", () => {
    if (request.readyState == 4 && request.status == 200) {
      callback(undefined, JSON.parse(request.responseText));
    } else if (request.readyState == 4) {
        callback("could not fetch data", undefined);
    }
  });

  request.open("GET", URL);
  request.send();
};

const URL1 = "https://jsonplaceholder.typicode.com/todos/1";
const URL2 = "https://jsonplaceholder.typicode.com/posts/1";
const URL3 = "https://jsonplaceholder.typicode.com/users/1";

getRequest(URL1, (err, data) => {
    if(!err) {
        console.log(data);
        getRequest(URL2, (err, data) => {
            if(!err) {
                console.log(data);
                getRequest(URL3, (err, data) => {
                    if(!err) {
                        console.log(data);
                    }
                    else {
                        console.log(err);
                    }
                })
            }
            else {
                console.log(err);
            }
        })
    }
    else {
        console.log(err);
    }
});