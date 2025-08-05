function something() {
   var sum = 0;
    for (var i = 0; i < 10000000000000; i++) {
      sum += i;
    }

    console.log("The sum is : " + sum);
    
}

console.log("BEFORE");
something();
console.log("AFTER");