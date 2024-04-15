function saveEmotion() {
    const inputElement = document.getElementById('emotionInput');
    const emotion = inputElement.value;

    fetch('/api/recommend', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ emotion: emotion })
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('emotionDisplay').textContent = '추천 결과: ' + data.message;
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('emotionDisplay').textContent = '추천을 받아오는 데 실패했습니다.';
    });
}
