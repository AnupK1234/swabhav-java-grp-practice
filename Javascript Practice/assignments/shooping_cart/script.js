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

  const totalPrice = productPrice * quantity;

  const tableBody = document
    .getElementById("cart-table")
    .querySelector("tbody");

  const row = document.createElement("tr");
  row.innerHTML = `
        <td>${productName}</td>
        <td>${quantity}</td>
        <td>₹${productPrice.toLocaleString()}</td>
        <td>₹${totalPrice.toLocaleString()}</td>
      `;

  tableBody.appendChild(row);
}
