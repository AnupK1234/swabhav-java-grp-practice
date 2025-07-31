const layoutBtn = document.getElementById("layoutToggle");
const themeBtn = document.getElementById("themeToggle");
const resume = document.getElementById("resume");

layoutBtn.onclick = () => {
  resume.classList.toggle("single-column");
  resume.classList.toggle("two-column");
};

themeBtn.onclick = () => {
  resume.classList.toggle("light-mode");
  resume.classList.toggle("dark-mode");
};
