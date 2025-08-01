//A closure gives you access to an outer function’s variables from an inner function even after the outer function has returned.
//example 1
// function x(){
//     var a=7;
//     function y(){
//         console.log(a);
//     }
//     y();
// }
// x();

//example 2
// function x(){
//     var a=7;
//     function y(){
//         console.log(a);
//     }
//     a=100;
//    return y;
// }
// var z= x();
// console.log(z);
// z();

function z() {
  var b = 900;
  function x() {
    var a = 7;
    function y() {
      console.log(a, b);
    }
    y();
  }
  x();
}

z();


// new example
// function outerFunction() {
//     let outerVariable = "I am outside!";

//     function innerFunction() {
//         console.log(outerVariable);  
//     }

//     return innerFunction;
// }

// const myClosure = outerFunction(); 
// console.log(myClosure);
// myClosure(); 
