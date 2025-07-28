function addToTable(event) {
  event.preventDefault();
  // Get input values
  const id = document.getElementById("id").value;
  const firstName = document.getElementById("firstName").value;
  const lastName = document.getElementById("lastName").value;
  const email = document.getElementById("email").value;

  if (!id || !firstName || !lastName || !email) {
    alert("Please complete the form");
    return;
  }

  // Create new row and cells
  const table = document
    .getElementById("userTable")
    .getElementsByTagName("tbody")[0];
  const newRow = table.insertRow();

  newRow.insertCell(0).textContent = id;
  newRow.insertCell(1).textContent = firstName;
  newRow.insertCell(2).textContent = lastName;
  newRow.insertCell(3).textContent = email;

  // Clear form fields
  document.getElementById("userForm").reset();
}
