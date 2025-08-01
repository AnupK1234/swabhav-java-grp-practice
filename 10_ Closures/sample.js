//A closure gives you access to an outer function’s variables from an inner function even after the outer function has returned.

// example 1
// function outerFunction(){
//     let outerVariable="I am Outside";
//     function innerfunction(){
//         console.log(outerVariable);
//     }
//     return innerfunction;
// }
// const myClouser=outerFunction();
// console.log(myClouser);
// myClouser();

//example 2
function counter() {
    let count = 0;

    return function() {
        count++;
        console.log(`Current count: ${count}`);
    };
}

const increment = counter();
increment(); 
increment(); 

//example 3
function createAccount(initialBalance) {
    let balance = initialBalance;

    return {
        deposit: function(amount) {
            balance += amount;
            console.log(`Deposited ₹${amount}. New Balance: ₹${balance}`);
        },
        withdraw: function(amount) {
            if (amount <= balance) {
                balance -= amount;
                console.log(`Withdrew ₹${amount}. Remaining Balance: ₹${balance}`);
            } else {
                console.log("Insufficient funds!");
            }
        },
        checkBalance: function() {
            console.log(`Current Balance: ₹${balance}`);
        }
    };
}

const myAccount = createAccount(1000);

myAccount.deposit(500);    
myAccount.withdraw(200);     
myAccount.checkBalance();    

