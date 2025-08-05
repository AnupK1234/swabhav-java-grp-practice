// MyModule.js

const MyModule = (function () {
  // Private variable
  let privateVar = "I am private";

  // Private function
  function privateFunction() {
    console.log("Private function called");
  }

  // Public API
  return {
    publicVar: "I am public",

    publicFunction: function () {
      console.log("Public function called");
      console.log(privateVar);
      privateFunction();
    },
  };
})();

// 👇 EXPORT the module
module.exports = MyModule;
