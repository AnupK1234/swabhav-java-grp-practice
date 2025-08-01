let person = {
    f_name: "Anup",
    l_name: "Khismatrao",

    fullName: function() {
      return this.f_name + " " + this.l_name;
    }
} 

// console.log(person.fullName());

// console.log(this);
let obj = {
        firstname : "anup",
        lastname : "khismatrao",
    }
function greet() {
    let name = "anup";

    function greet2() {
        console.log(obj);
    }
    greet2();
}
greet()


// this

// this window    globalThis : same working

// console.log(this);
// console.log(globalThis);
