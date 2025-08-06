const addBtn = document.getElementById('addBtn');
const startBtn = document.getElementById('startBtn');
const textInput = document.getElementById('textInput');
const dataTable = document.querySelector('#dataTable tbody');
const result = document.getElementById('result');

let rowCount = 0;

// Add user input to table
addBtn.addEventListener('click', () => {
  const text = textInput.value.trim();
  if (text === "") return;

  rowCount++;
  const row = document.createElement('tr');
  row.innerHTML = `<td>${rowCount}</td><td>${text}</td>`;
  dataTable.appendChild(row);

  textInput.value = '';
  textInput.focus();
});

// Start heavy task on main thread (⚠️ this will block the UI)
startBtn.addEventListener('click', () => {
  result.innerText = "⏳ Counting on main thread...";
  
  let sum = 0;
  for (let i = 1; i <= 10000000000; i++) {
    sum += i;
  }

  result.innerText = `✅ Finished! Counted to: ${sum}`;
});
