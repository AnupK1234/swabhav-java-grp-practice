const products = [
  { id: 1, name: 'Laptop', category: 'Electronics', price: 1200, stock: 10 },
  { id: 2, name: 'Keyboard', category: 'Electronics', price: 75, stock: 25 },
  { id: 3, name: 'Mouse', category: 'Electronics', price: 30, stock: 50 },
  { id: 4, name: 'Desk Chair', category: 'Furniture', price: 300, stock: 5 },
  { id: 5, name: 'Monitor', category: 'Electronics', price: 400, stock: 15 },
  { id: 6, name: 'Notebook', category: 'Stationery', price: 15, stock: 100 },
];

// Example 1: Get an array of just the product names
const productNames = products.map(product => product.name);
console.log('Product Names:', productNames);

// Example 2: Apply a 10% discount to all product prices
const discountedProducts = products.map(product => ({
  ...product, // Spread 
  price: product.price * 0.90
}));
console.log('\nProducts with 10% Discount:', discountedProducts);

