  const layoutToggleButton = document.getElementById("layout-toggle");
  const themeToggleButton = document.getElementById("theme-toggle");
  const resumeContainer = document.querySelector(".resume-container");
  const themeIcon = themeToggleButton.querySelector(".icon");

  const savedLayout = localStorage.getItem("resumeLayout");
  if (savedLayout === "two-column") {
    resumeContainer.classList.add("two-column");
  }
  updateLayoutIcon();

  const savedTheme = localStorage.getItem("resumeTheme");
  if (savedTheme === "dark-mode") {
    document.body.classList.add("dark-mode");
  }
  updateThemeIcon();

  layoutToggleButton.addEventListener("click", () => {
    resumeContainer.classList.toggle("two-column");
    const currentLayout = resumeContainer.classList.contains("two-column")
      ? "two-column"
      : "single-column";
    localStorage.setItem("resumeLayout", currentLayout);
    updateLayoutIcon();
  });

  themeToggleButton.addEventListener("click", () => {
    document.body.classList.toggle("dark-mode");
    const currentTheme = document.body.classList.contains("dark-mode")
      ? "dark-mode"
      : "light-mode";
    localStorage.setItem("resumeTheme", currentTheme);
    updateThemeIcon();
  });

  function updateLayoutIcon() {
  }

  function updateThemeIcon() {
    if (document.body.classList.contains("dark-mode")) {
      themeIcon.textContent = "💡";
    } else {
      themeIcon.textContent = "🌙";
    }
  }
