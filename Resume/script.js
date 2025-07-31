document.addEventListener('DOMContentLoaded', () => {
  const layoutToggle = document.getElementById('layout-toggle');
  const themeToggle = document.getElementById('theme-toggle');
  const container = document.getElementById('resume-container');
  const layoutLabel = document.getElementById('toggle-label');
  const themeLabel = document.getElementById('theme-label');


  layoutToggle.checked = false; 
  container.classList.remove('two-column'); 
  layoutLabel.textContent = 'Activate Two-Column View';

  
  themeToggle.checked = false;
  document.body.classList.remove('dark-mode');
  themeLabel.textContent = 'Activate Dark Mode';


  layoutToggle.addEventListener('change', () => {
    if (layoutToggle.checked) {
      container.classList.add('two-column');
      layoutLabel.textContent = 'Switch to Single-Column View';
    } else {
      container.classList.remove('two-column');
      layoutLabel.textContent = 'Activate Two-Column View';
    }
  });

 
  themeToggle.addEventListener('change', () => {
    if (themeToggle.checked) {
      document.body.classList.add('dark-mode');
      themeLabel.textContent = 'Switch to Light Mode';
    } else {
      document.body.classList.remove('dark-mode');
      themeLabel.textContent = 'Activate Dark Mode';
    }
  });
});
