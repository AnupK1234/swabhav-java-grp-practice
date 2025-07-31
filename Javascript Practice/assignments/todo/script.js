// Load tasks from localStorage on page load
window.onload = function () {
  const savedTasks = JSON.parse(localStorage.getItem("tasks")) || [];
  savedTasks.forEach((taskText) => createTaskElement(taskText));
};

function addTask() {
  const input = document.getElementById("todo-input");
  const taskText = input.value.trim();

  if (taskText === "") {
    alert("Please enter a task.");
    return;
  }

  createTaskElement(taskText);
  saveTask(taskText);
  input.value = "";
}

function createTaskElement(taskText) {
  const li = document.createElement("li");
  li.className = "todo-item";

  const span = document.createElement("span");
  span.innerText = taskText;

  const editBtn = document.createElement("button");
  editBtn.innerText = "Edit";
  editBtn.className = "edit-btn";
  editBtn.onclick = () => {
    const newText = prompt("Edit task:", span.innerText);
    if (newText !== null && newText.trim() !== "") {
      const updatedText = newText.trim();
      updateTask(span.innerText, updatedText);
      span.innerText = updatedText;
    }
  };

  const deleteBtn = document.createElement("button");
  deleteBtn.innerText = "Delete";
  deleteBtn.onclick = () => {
    deleteTask(span.innerText);
    li.remove();
  };

  li.appendChild(span);
  li.appendChild(editBtn);
  li.appendChild(deleteBtn);

  document.getElementById("todo-list").appendChild(li);
}

// LocalStorage functions
function saveTask(taskText) {
  const tasks = JSON.parse(localStorage.getItem("tasks")) || [];
  tasks.push(taskText);
  localStorage.setItem("tasks", JSON.stringify(tasks));
}

function deleteTask(taskText) {
  let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
  tasks = tasks.filter((t) => t !== taskText);
  localStorage.setItem("tasks", JSON.stringify(tasks));
}

function updateTask(oldText, newText) {
  let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
  const index = tasks.indexOf(oldText);
  if (index !== -1) {
    tasks[index] = newText;
    localStorage.setItem("tasks", JSON.stringify(tasks));
  }
}
