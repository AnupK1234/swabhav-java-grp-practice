let cart = [];

    function addToCart() {
      const productSelect = document.getElementById('product');
      const quantityInput = document.getElementById('quantity');

      const selectedOption = productSelect.options[productSelect.selectedIndex];
      const id = selectedOption.value;
      const name = selectedOption.getAttribute('data-name');
      const price = parseInt(selectedOption.getAttribute('data-price'));
      const quantity = parseInt(quantityInput.value);

      if (!quantity || quantity < 1) {
        alert("Please enter a valid quantity.");
        return;
      }

      const totalPrice = (price * quantity);

      cart.push({ id, name, quantity, totalPrice });

      // Update table
      updateCartTable();
    }

    function updateCartTable() {
      const tbody = document.querySelector('#cartTable tbody');
      tbody.innerHTML = ''; 

      cart.forEach((item) => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${item.id}</td>
          <td>${item.name}</td>
          <td>${item.quantity}</td>
          <td>${item.totalPrice}</td>
        `;
        tbody.innerHTML += row.innerHTML;
      });
    }