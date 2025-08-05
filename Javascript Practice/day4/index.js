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

// console.log(myOttList);

//find list of all active subscription
const activeSub = myOttList.filter((item) => item.subscription === "active");
// console.log(activeSub);

const priceArr = myOttList.map((item) => item.price);
// console.log(priceArr);

const sumOfOTT = myOttList.reduce((acc, num) => acc + num.price, 0);
// console.log("SUM OF OTT IS : " + sumOfOTT);

// cnt of active and inactive
// console.log("NUMBER OF ACTIVE SUB : " + activeSub.length);
// console.log("NUMBER OF INACTIVE SUB : " + (myOttList.length - activeSub.length));

// price > 900 then 30% discount
const saleList = myOttList.map((item) => {
  if (item.price > 900)
    return {
      ...item,
      price: item.price - 0.3 * item.price,
    };
  else return item;
});

// console.log(saleList);

myOttList.sort((a, b) => a.name.localeCompare(b.name));
// console.log(myOttList);


