var myModule = (function() {
  // Private variables and functions
  var privateVariable = 'I am private';

  function privateFunction() {
    console.log(privateVariable);
  }

  // Public methods and properties
  return {
    publicMethod: function() {
      console.log('I am public');
      privateFunction();
    },
    publicVariable: 'I am also public'
  };
})();

myModule.publicMethod(); // "I am public", "I am private"
console.log(myModule.publicVariable); // "I am also public"
console.log(myModule.privateVariable); // undefined