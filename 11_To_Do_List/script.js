document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("todo-form");
  const input = document.getElementById("todo-input");
  const list = document.getElementById("todo-list");

  loadTasks();

  form.addEventListener("submit", (e) => {
    e.preventDefault();
    const text = input.value.trim();
    if (text !== "") {
      addTask(text);
      input.value = "";
      saveTasks();
    }
  });

  function addTask(text, completed = false) {
    const li = document.createElement("li");
    if (completed) li.classList.add("completed");

    const span = document.createElement("span");
    span.className = "task-text";
    span.textContent = text;

  
    span.addEventListener("click", () => {
      li.classList.toggle("completed");
      saveTasks();
    });

    const actions = document.createElement("div");
    actions.className = "task-actions";

    const editBtn = document.createElement("button");
    editBtn.innerHTML = '<i class="fa-solid fa-pen-to-square"></i>';
    editBtn.className = "edit";
    editBtn.addEventListener("click", () => {
      if (span.isContentEditable) {
        span.contentEditable = false;
        editBtn.innerHTML = '<i class="fa-solid fa-pen-to-square"></i>';
        span.style.border = "none";
        saveTasks();
      } else {
        span.contentEditable = true;
        span.focus();
        span.style.border = "1px dashed #aaa";
        editBtn.innerHTML = '<i class="fa-solid fa-check"></i>';
      }
    });

    const deleteBtn = document.createElement("button");
    deleteBtn.innerHTML = '<i class="fa-solid fa-trash"></i>';
    deleteBtn.className = "delete";
    deleteBtn.addEventListener("click", () => {
      li.remove();
      saveTasks();
    });

    actions.appendChild(editBtn);
    actions.appendChild(deleteBtn);

    li.appendChild(span);
    li.appendChild(actions);
    list.appendChild(li);
  }

  function saveTasks() {
    const tasks = [];
    document.querySelectorAll("#todo-list li").forEach((li) => {
      const text = li.querySelector(".task-text").textContent;
      const completed = li.classList.contains("completed");
      tasks.push({ text, completed });
    });
    localStorage.setItem("sleekTasks", JSON.stringify(tasks));
  }

  function loadTasks() {
    const tasks = JSON.parse(localStorage.getItem("sleekTasks")) || [];
    tasks.forEach((task) => addTask(task.text, task.completed));
  }
});
