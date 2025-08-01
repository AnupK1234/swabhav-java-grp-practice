const products = [
  { id: 1, name: 'Laptop', category: 'Electronics', price: 1200, stock: 10 },
  { id: 2, name: 'Keyboard', category: 'Electronics', price: 75, stock: 25 },
  { id: 3, name: 'Mouse', category: 'Electronics', price: 30, stock: 50 },
  { id: 4, name: 'Desk Chair', category: 'Furniture', price: 300, stock: 5 },
  { id: 5, name: 'Monitor', category: 'Electronics', price: 400, stock: 15 },
  { id: 6, name: 'Notebook', category: 'Stationery', price: 15, stock: 100 },
];

// Example 1: Get all products in the 'Electronics' category
const electronicsProducts = products.filter(product => product.category === 'Electronics');
console.log('\nElectronics Products:', electronicsProducts);

// Example 2: Get products with stock less than 15
const lowStockProducts = products.filter(product => product.stock < 15);
console.log('\nLow Stock Products (stock < 15):', lowStockProducts);
