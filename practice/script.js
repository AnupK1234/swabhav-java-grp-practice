let array = [45,43,29,23];
let array2 = [1,2,3,5,6];
// array map method
// const a = array.forEach((value) => {
//     console.log(value);
    
// });
// console.log(a);


// const b = array.map((value, index, array) => {
//     console.log(value, index, array);
//     return value*index
// })

// console.log(b);





//array filter method
const b = array.filter((value) => {
    return value > 50;
})

// console.log(b);

//array reduce method
const c = array2.reduce((h1,h2)=> {
    return h1+h2;
})

console.log(c);

