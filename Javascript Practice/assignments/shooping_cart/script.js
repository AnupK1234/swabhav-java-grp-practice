let cartList = [];

function renderCart() {
  const tableBody = document
    .getElementById("cart-table")
    .querySelector("tbody");
  tableBody.innerHTML = "";

  cartList.forEach((item, index) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${item.name}</td>
      <td>
        <button onclick="updateQuantity(${index}, -1)">-</button>
        <span id="qty-${index}">${item.quantity}</span>
        <button onclick="updateQuantity(${index}, 1)">+</button>
      </td>
      <td>₹${item.price.toLocaleString()}</td>
      <td id="total-${index}">₹${(
      item.price * item.quantity
    ).toLocaleString()}</td>
    `;
    tableBody.appendChild(row);
  });
}

function addToCart() {
  const productSelect = document.getElementById("product");
  const selectedOption = productSelect.options[productSelect.selectedIndex];
  const productName = selectedOption.value;
  const productPrice = parseFloat(selectedOption.getAttribute("data-price"));
  const quantity = parseInt(document.getElementById("quantity").value);

  if (isNaN(quantity) || quantity <= 0) {
    alert("Please enter a valid quantity.");
    return;
  }

  // Check if product already exists in cart
  const existingIndex = cartList.findIndex((item) => item.name === productName);
  if (existingIndex !== -1) {
    cartList[existingIndex].quantity += quantity;
  } else {
    cartList.push({
      id: productSelect.selectedIndex,
      name: productName,
      price: productPrice,
      quantity,
    });
  }

  renderCart();
}

function updateQuantity(index, change) {
  cartList[index].quantity += change;
  if (cartList[index].quantity <= 0) {
    cartList.splice(index, 1); // remove item
  }
  renderCart();
}
