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
        const parsedData = JSON.parse(data.message);
//        document.getElementById('emotionDisplay').textContent = '감정 예측은: ' + parsedData.emotion;
        document.getElementById('emotionDisplay').textContent = '감정 예측은: ' + parsedData.recommended_track;
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('emotionDisplay').textContent = '글을 좀 더 자세히 적어주세요';
    });
}
