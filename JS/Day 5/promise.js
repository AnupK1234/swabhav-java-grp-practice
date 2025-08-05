const getSomething = () => {
  return new Promise((resolve, reject) => {
    resolve("promise resolved");
  });
};

getSomething().then(
  (data) => {
    console.log("Data: " + data);
  },
  (err) => {
    console.log("Error: " + err);
  }
);
