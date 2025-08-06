const worker = new Worker('worker.js');

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

// Start heavy task in background
startBtn.addEventListener('click', () => {
  result.innerText = "⏳ Counting in background...";
  worker.postMessage('start');
});

// Receive result from worker
worker.onmessage = function (e) {
  result.innerText = `✅ Finished! Counted to: ${e.data}`;
};
