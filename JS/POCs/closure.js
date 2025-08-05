function outerFunction() {
  let outerVariable = "I am from the outer function!";

  function innerFunction() {
    console.log(outerVariable); // Accessing outerVariable
  }

  return innerFunction;
}

const myClosure = outerFunction(); // outerFunction finishes execution
myClosure(); // innerFunction is called, still accessing outerVariable