document.getElementById('addBtn').addEventListener('click', function () {
  const productSelect = document.getElementById('product');
  const quantityInput = document.getElementById('quantity');
  const cartTableBody = document.querySelector('#cartTable tbody');

  const selectedOption = productSelect.options[productSelect.selectedIndex];
  const productName = selectedOption.value;
  const unitPrice = parseInt(selectedOption.getAttribute('data-price'));
  const quantity = parseInt(quantityInput.value);

  if (!productName || isNaN(quantity) || quantity <= 0) {
    alert('Please select a product and enter a valid quantity.');
    return;
  }

  const totalPrice = unitPrice * quantity;

  const newRow = document.createElement('tr');
  newRow.innerHTML = `
    <td>${productName}</td>
    <td>${quantity}</td>
    <td>₹${unitPrice.toLocaleString()}</td>
    <td>₹${totalPrice.toLocaleString()}</td>
  `;

  cartTableBody.appendChild(newRow);

  // Reset form
  productSelect.selectedIndex = 0;
  quantityInput.value = '';
});
