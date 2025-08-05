document.addEventListener("DOMContentLoaded", () => {
  const products = [
    { id: "butter-chicken", name: "Butter Chicken", price: 650.0 },
    { id: "naan", name: "Garlic Naan", price: 60.5 },
    { id: "icecream", name: "Anjeer Icecream", price: 399.75 },
    { id: "biryani", name: "Chicken Biryani", price: 450.0 },
    { id: "kebab", name: "Seekh Kebab", price: 520.0 },
  ];

  const productSelect = document.getElementById("product-select");
  const quantitySelect = document.getElementById("quantity-select");
  const addToCartBtn = document.getElementById("add-to-cart-btn");
  const cartDisplay = document.getElementById("cart-display");
  const emptyCartMessage = document.getElementById("empty-cart-message");
  const cartSummary = document.getElementById("cart-summary");
  const cartTotalElement = document.getElementById("cart-total");

  let cart = [];

  const populateProductDropdown = () => {
    productSelect.innerHTML = "";
    products.forEach((product) => {
      const option = document.createElement("option");
      option.value = product.id;
      option.textContent = product.name;
      option.dataset.price = product.price;
      productSelect.appendChild(option);
    });
  };

  const formatCurrency = (amount) => `₹${amount.toFixed(2)}`;

  const updateCartDisplay = () => {
    cartDisplay.innerHTML = "";
    if (cart.length === 0) {
      cartDisplay.appendChild(emptyCartMessage);
      cartSummary.classList.add("hidden");
      return;
    }

    if (document.body.contains(emptyCartMessage)) {
      emptyCartMessage.remove();
    }

    cartSummary.classList.remove("hidden");

    let totalCartPrice = 0;

    cart.forEach((item, index) => {
      const lineItemTotal = item.quantity * item.price;
      totalCartPrice += lineItemTotal;

      const cartItemDiv = document.createElement("div");
      cartItemDiv.classList.add(
        "flex",
        "items-center",
        "justify-between",
        "p-4",
        "bg-white",
        "rounded-lg",
        "shadow-sm"
      );
      cartItemDiv.dataset.index = index;

      cartItemDiv.innerHTML = `
        <div class="flex-grow">
          <h3 class="text-lg font-medium">${item.name}</h3>
          <p class="text-sm text-gray-500">Unit Price: ${formatCurrency(
            item.price
          )}</p>
        </div>
        <div class="flex items-center space-x-2">
          <div class="flex items-center space-x-1">
            <button class="quantity-btn decrement bg-gray-200 text-gray-700 rounded-full w-6 h-6 flex items-center justify-center text-sm font-bold">-</button>
            <span class="text-lg font-semibold w-6 text-center">${
              item.quantity
            }</span>
            <button class="quantity-btn increment bg-gray-200 text-gray-700 rounded-full w-6 h-6 flex items-center justify-center text-sm font-bold">+</button>
          </div>
          <span class="text-lg font-semibold w-24 text-right">${formatCurrency(
            lineItemTotal
          )}</span>
          <button class="remove-item-btn ml-4 text-red-500 hover:text-red-700 transition-colors duration-200" aria-label="Remove item">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
              <path fill-rule="evenodd" d="M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.728-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z" clip-rule="evenodd" />
            </svg>
          </button>
        </div>
      `;
      cartDisplay.appendChild(cartItemDiv);
    });

    cartTotalElement.textContent = formatCurrency(totalCartPrice);
  };

  const handleQuantityChange = (index, delta) => {
    if (cart[index]) {
      const newQuantity = cart[index].quantity + delta;
      if (newQuantity > 0) {
        cart[index].quantity = newQuantity;
      } else {
        cart.splice(index, 1);
      }
      updateCartDisplay();
    }
  };

  const handleRemoveItem = (index) => {
    cart.splice(index, 1);
    updateCartDisplay();
  };

  addToCartBtn.addEventListener("click", () => {
    const selectedOption = productSelect.options[productSelect.selectedIndex];
    const productName = selectedOption.textContent;
    const unitPrice = parseFloat(selectedOption.dataset.price);
    const quantity = parseInt(quantitySelect.value, 10);

    const existingItem = cart.find((item) => item.name === productName);

    if (existingItem) {
      existingItem.quantity += quantity;
    } else {
      cart.push({
        name: productName,
        price: unitPrice,
        quantity: quantity,
      });
    }

    updateCartDisplay();
  });

  cartDisplay.addEventListener("click", (event) => {
    const itemDiv = event.target.closest(".flex.items-center.justify-between");
    if (!itemDiv) return;

    const index = parseInt(itemDiv.dataset.index, 10);

    if (event.target.closest(".remove-item-btn")) {
      handleRemoveItem(index);
    } else if (event.target.closest(".quantity-btn.increment")) {
      handleQuantityChange(index, 1);
    } else if (event.target.closest(".quantity-btn.decrement")) {
      handleQuantityChange(index, -1);
    }
  });

  populateProductDropdown();
  updateCartDisplay();
});
