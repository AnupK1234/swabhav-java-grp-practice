function addTask() {
  const taskInput = document.getElementById("taskInput");
  const taskText = taskInput.value.trim();

  if (taskText === "") return;

  const taskList = document.getElementById("taskList");

  const li = document.createElement("li");
  li.innerText = taskText;

  const deleteBtn = document.createElement("button");
  deleteBtn.innerText = "Delete";
  deleteBtn.onclick = function () {
    taskList.removeChild(li);
  };

  li.appendChild(deleteBtn);
  taskList.appendChild(li);

  taskInput.value = "";
}
