// The Module Pattern in JavaScript is used to encapsulate private data and expose only public methods
// helping you organize code, avoid global scope pollution, and implement encapsulation.
// It uses IIFEs (Immediately Invoked Function Expressions) to create a scope where variables and functions can be hidden from the outside world.

// Key Benifits
// 1. Keeps code clean and organized, 2.Supports encapsulation (private/public separation)
// 3. Prevents namespace collisions


// ShoppingCart Module using IIFE
const ShoppingCart = (function () {
   
    let cart = [];

    
    function calculateTotal() {
        return cart.reduce((total, item) => total + item.price, 0);
    }

    
    return {
        addItem: function (item) {
            cart.push(item);
            console.log(`${item.name} added to the cart.`);
        },
        getTotal: function () {
            return calculateTotal();
        },
        showCart: function () {
            console.log("Cart contents:", cart);
        }
    };
})();

// Using the module
ShoppingCart.addItem({ name: "Apple", price: 30 });
ShoppingCart.addItem({ name: "Banana", price: 10 });

ShoppingCart.showCart(); 
console.log("Total:", ShoppingCart.getTotal()); 

console.log(ShoppingCart.cart); 


// cart and calculateTotal() are private.
// Only methods returned in the object (addItem, getTotal, showCart) are public.
// This encapsulation is what makes the Module Pattern powerful.