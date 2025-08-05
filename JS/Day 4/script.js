const myOttList = [
  {
    name: "hotstar",
    price: 1500,
    subscription: "active",
  },
  {
    name: "sonyLiv",
    price: 700,
    subscription: "inactive",
  },
  {
    name: "zee5",
    price: 500,
    subscription: "active",
  },
  {
    name: "jioCinema",
    price: 1000,
    subscription: "inactive",
  },
  {
    name: "prime videos",
    price: 1300,
    subscription: "active",
  },
];

console.log(myOttList);

//find list of all active subsciptions
const activeSubscriptions = myOttList.filter(item => item.subscription === "active");
console.log(activeSubscriptions);

//find list of price
const priceList = myOttList.map(item => {
return item.price;
})

console.log(priceList);

//sum of total ott price
const totalPrice = myOttList.reduce((sum, item) => sum + item.price, 0);
console.log(totalPrice);

// count of active and inactive
console.log(`Active subscriptions: ${activeSubscriptions.length}`);
console.log(`Active subscriptions: ${myOttList.length - activeSubscriptions.length}`);




// count of active and inactive
const subscriptionCounts = myOttList.reduce(
  (counts, item) => {
    if (item.subscription === "active") {
      counts.active += 1;
    } else if (item.subscription === "inactive") {
      counts.inactive += 1;
    }
    return counts;
  },
  { active: 0, inactive: 0 } 
);

console.log(subscriptionCounts);

// create a product sale list where ott price greater than equal to 900 will get 30%


// Use .map() to create a new array with discounted prices
const productSaleList = myOttList.map(item => {
  if (item.price >= 900) {
    // const discount = item.price * 0.30;
    const price = item.price - item.price * 0.30;
    return {
      name
    };
  } else {
    return {
      ...item,
      discount: 0,
      finalPrice: item.price
    };
  }
});

console.log(productSaleList);


//sort by ottname

const sortedList = myOttList.sort((a, b) => a.price - b.price);
console.log(sortedList);