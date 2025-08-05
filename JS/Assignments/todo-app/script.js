document.addEventListener("DOMContentLoaded", () => {
  const todoInput = document.getElementById("todo-input");
  const categoryInput = document.getElementById("category-input");
  const addTodoBtn = document.getElementById("add-todo-btn");
  const todoList = document.getElementById("todo-list");
  const errorMessage = document.getElementById("error-message");
  const themeToggle = document.getElementById("theme-toggle");
  const sunIcon = document.getElementById("sun-icon");
  const moonIcon = document.getElementById("moon-icon");
  const filterAllBtn = document.getElementById("filter-all");
  const categoryFiltersContainer = document.getElementById("category-filters");
  const noTodosMessage = document.getElementById("no-todos-message");

  let todos = JSON.parse(localStorage.getItem("todos")) || [];
  let currentFilterCategory = "all";

  const generateUniqueId = () => Date.now();

  const saveTodos = () => {
    localStorage.setItem("todos", JSON.stringify(todos));
  };

  const createTodoElement = (todo) => {
    const isCompletedClass = todo.completed
      ? "line-through text-gray-500 dark:text-gray-400"
      : "text-gray-900 dark:text-gray-100";
    const borderColorClass = todo.completed
      ? "border-green-400"
      : "border-gray-200 dark:border-gray-700";

    return `
      <div class="todo-item flex items-center bg-gray-50 dark:bg-[#2C333A] p-4 rounded-md shadow-sm border-l-4 ${borderColorClass} transition-all duration-200" data-id="${
      todo.id
      }">
          <input type="checkbox" class="todo-checkbox mr-4 h-5 w-5 text-indigo-600 dark:text-indigo-400 rounded focus:ring-indigo-500" ${
            todo.completed ? "checked" : ""
          }>
          <div class="flex-grow ${isCompletedClass}">
            <p class="text-lg font-medium">${todo.text}</p>
           <span class="text-sm text-gray-500 dark:text-gray-400 italic">${                            todo.category || "No Category"
           }</span>
           </div>
         <button class="delete-btn ml-4 p-2 rounded-full text-red-500 hover:bg-red-100 dark:hover:bg-red-900 transition-colors duration-200">
           🗑️
         </button>
        </div>
      `;
  };

  const renderTodos = () => {
    todoList.innerHTML = "";

    const filteredTodos =
      currentFilterCategory === "all"
        ? todos
        : todos.filter(
            (todo) =>
              todo.category.toLowerCase() ===
              currentFilterCategory.toLowerCase()
          );

    if (filteredTodos.length === 0) {
      noTodosMessage.style.display = "block";
    } else {
      noTodosMessage.style.display = "none";
      filteredTodos.forEach((todo) => {
        todoList.innerHTML += createTodoElement(todo);
      });
    }

    attachTodoItemEventListeners();
  };

  const renderCategoryFilters = () => {
    categoryFiltersContainer.innerHTML = "";

    const categories = [
      "all",
      ...new Set(
        todos.map((todo) => todo.category.toLowerCase()).filter((cat) => cat)
      ),
    ];

    categories.forEach((category) => {
      if (category === "all" && categories.length > 1) return;
      if (category === "all" && todos.length === 0) return;

      const isActive = category === currentFilterCategory;
      const buttonClass = isActive
        ? "bg-indigo-600 text-white dark:bg-indigo-500 dark:text-white"
        : "bg-indigo-100 text-indigo-800 dark:bg-indigo-700 dark:text-indigo-100 hover:bg-indigo-200 dark:hover:bg-indigo-600";

      if (category === "all") {
        filterAllBtn.className = `filter-btn px-3 py-1 rounded-full text-sm font-medium transition-colors duration-200 ${
          isActive
            ? "bg-indigo-600 text-white dark:bg-indigo-500 dark:text-white"
            : "bg-indigo-100 text-indigo-800 dark:bg-indigo-700 dark:text-indigo-100 hover:bg-indigo-200 dark:hover:bg-indigo-600"
        }`;
      } else {
        const button = document.createElement("button");
        button.textContent =
          category.charAt(0).toUpperCase() + category.slice(1);
        button.className = `filter-btn px-3 py-1 rounded-full text-sm font-medium transition-colors duration-200 ${buttonClass}`;
        button.dataset.category = category;
        categoryFiltersContainer.appendChild(button);
      }
    });

    attachCategoryFilterEventListeners();
  };

  const addTodo = () => {
    const todoText = todoInput.value.trim();
    const categoryText = categoryInput.value.trim();

    if (todoText === "") {
      errorMessage.style.display = "block";
      return;
    }

    errorMessage.style.display = "none";

    const newTodo = {
      id: generateUniqueId(),
      text: todoText,
      category: categoryText,
      completed: false,
    };

    todos.push(newTodo);
    saveTodos();
    renderTodos();
    renderCategoryFilters();
    todoInput.value = "";
    categoryInput.value = "";
  };

  const toggleTodoCompletion = (id) => {
    todos = todos.map((todo) =>
      todo.id === id ? { ...todo, completed: !todo.completed } : todo
    );
    saveTodos();
    renderTodos();
  };

  const deleteTodo = (id) => {
    todos = todos.filter((todo) => todo.id !== id);
    saveTodos();
    renderTodos();
    renderCategoryFilters();
  };

  const attachTodoItemEventListeners = () => {
    document.querySelectorAll(".todo-checkbox").forEach((checkbox) => {
      checkbox.onchange = (event) => {
        const todoId = parseInt(event.target.closest(".todo-item").dataset.id);
        toggleTodoCompletion(todoId);
      };
    });

    document.querySelectorAll(".delete-btn").forEach((button) => {
      button.onclick = (event) => {
        const todoId = parseInt(event.target.closest(".todo-item").dataset.id);
        deleteTodo(todoId);
      };
    });
  };

  const attachCategoryFilterEventListeners = () => {
    document.querySelectorAll(".filter-btn").forEach((button) => {
      button.onclick = (event) => {
        const category = event.target.dataset.category;
        currentFilterCategory = category;
        renderTodos();
        renderCategoryFilters();
      };
    });
  };

  const toggleTheme = () => {
    const isDarkMode = document.documentElement.classList.toggle("dark");
    localStorage.setItem("theme", isDarkMode ? "dark" : "light");
    updateThemeIcons(isDarkMode);
  };

  const updateThemeIcons = (isDarkMode) => {
    if (isDarkMode) {
      moonIcon.style.display = "block";
      sunIcon.style.display = "none";
    } else {
      moonIcon.style.display = "none";
      sunIcon.style.display = "block";
    }
  };

  const initializeApp = () => {
    const savedTheme = localStorage.getItem("theme");
    if (savedTheme === "dark") {
      document.documentElement.classList.add("dark");
      updateThemeIcons(true);
    } else {
      document.documentElement.classList.remove("dark");
      updateThemeIcons(false);
    }

    renderTodos();
    renderCategoryFilters();

    addTodoBtn.addEventListener("click", addTodo);
    todoInput.addEventListener("keypress", (e) => {
      if (e.key === "Enter") {
        addTodo();
      }
    });
    categoryInput.addEventListener("keypress", (e) => {
      if (e.key === "Enter") {
        addTodo();
      }
    });
    themeToggle.addEventListener("click", toggleTheme);
    filterAllBtn.addEventListener("click", (e) => {
      currentFilterCategory = "all";
      renderTodos();
      renderCategoryFilters();
    });
  };

  initializeApp();
});

tailwind.config = {
  darkMode: "class",
};
