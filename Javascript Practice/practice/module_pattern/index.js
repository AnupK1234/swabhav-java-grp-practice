const MyModule = require("./MyModule");

MyModule.publicFunction(); // ✅ Works
console.log(MyModule.publicVar); // ✅ I am public

// console.log(MyModule.privateVar); // ❌ undefined
// MyModule.privateFunction(); // ❌ TypeError
