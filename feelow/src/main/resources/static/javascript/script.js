function saveEmotion() {
    var emotion = document.getElementById("emotionInput").value;
    var emotionDisplay = document.getElementById("emotionDisplay");
  
    if (emotion.trim() !== "") {
      emotionDisplay.textContent = "오늘의 감정: " + emotion;
    } else {
      emotionDisplay.textContent = "감정을 입력해주세요.";
    }
  }
  