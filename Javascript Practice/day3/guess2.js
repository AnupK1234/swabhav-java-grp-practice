let secretNumber = Math.floor(Math.random() * 30) + 1;
console.log(secretNumber);

function check(btnRef) {
  let pressedBtnId = btnRef.id;
  console.log("Pressed button is : " + btnRef.id);
  const message = document.getElementById("message");
  const pressedBtnRef = document.getElementById(btnRef.id);

  pressedBtnRef.setAttribute("disabled", "");

  if (pressedBtnId == secretNumber) {
    message.textContent = `✅ Correct! The number was ${secretNumber}`;
    pressedBtnRef.style.backgroundColor = "green";
  } else {
    if (pressedBtnId < secretNumber) {
      pressedBtnRef.style.backgroundColor = "red";
      message.textContent = "📉 Too low!";
    } else {
      pressedBtnRef.style.backgroundColor = "yellow";
      message.textContent = "📈 Too high!";
    }
  }
}

function resetGame() {
  location.reload();
}
