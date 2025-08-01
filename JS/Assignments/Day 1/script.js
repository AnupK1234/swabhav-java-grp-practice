let students = [
  {
    id: 101,
    firstName: "Nilesh",
    lastName: "Gawli",
    email: "nbgawli@gmail.com",
  },
  {
    id: 102,
    firstName: "MS",
    lastName: "Dhoni",
    email: "msd@gmail.com",
  },
  {
    id: 103,
    firstName: "Rohit",
    lastName: "Sharma",
    email: "woh@gmail.com",
  },
  {
    id: 104,
    firstName: "Shikhar",
    lastName: "Dhawan",
    email: "sd@gmail.com",
  },
];

// reference from dom elements
const studentTableBody = document.getElementById("studentTableBody");
const studentForm = document.getElementById("studentForm");
const firstNameInput = document.getElementById("firstName");
const lastNameInput = document.getElementById("lastName");
const emailInput = document.getElementById("email");

function renderStudentTable() {
  let html = ``; 
  students.forEach((student) => {
    html += `
            <tr class="hover:bg-gray-50 transition duration-150 ease-in-out">
                <td class="py-3 px-4">${student.id}</td>
                <td class="py-3 px-4">${student.firstName}</td>
                <td class="py-3 px-4">${student.lastName}</td>
                <td class="py-3 px-4">${student.email}</td>
            </tr>
            `;
  });
  studentTableBody.innerHTML = html;
}

function generateNewId() {
  if (students.length === 0) {
    return 101; 
  }
  const maxId = Math.max(...students.map((student) => student.id));
  return maxId + 1;
}

studentForm.addEventListener("submit", function (event) {
  event.preventDefault(); 

  const newFirstName = firstNameInput.value.trim();
  const newLastName = lastNameInput.value.trim();
  const newEmail = emailInput.value.trim();

  if (!newFirstName || !newLastName || !newEmail) {
    console.error("Please fill in all fields.");
    alert("Please fill in all fields.")
    return;
  }

  const newId = generateNewId();

  const newStudent = {
    id: newId,
    firstName: newFirstName,
    lastName: newLastName,
    email: newEmail,
  };

  students.push(newStudent);

  renderStudentTable();

  firstNameInput.value = "";
  lastNameInput.value = "";
  emailInput.value = "";
});

renderStudentTable();
