function outerFunction(){
    let ourterVar= "I am Outside";
    return function innerfunction(){
        console.log(ourterVar);
    }; 
    
}
const z= outerFunction();
console.log(z);
z();
