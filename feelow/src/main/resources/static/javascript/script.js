function saveEmotion(event) {
    document.getElementById('loading').style.display = 'block';
    let emotionText = document.getElementById('emotionInput').value;
    fetch('http://localhost:8080/api/recommend', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ sentence: emotionText })
    })
    .then(response => response.json())
    .then(data => {
        const responseData = JSON.parse(data.message);
        document.getElementById('loading').style.display = 'none';

        document.getElementById('emotionDisplay').textContent = `감정: ${responseData.emotion}`;

        if (responseData.recommended_track && responseData.recommended_artist) {
            document.getElementById('trackInfo').textContent = `추천 곡: ${responseData.recommended_track} by ${responseData.recommended_artist}`;
        } else {
            document.getElementById('trackInfo').textContent = "추천 곡을 찾을 수 없습니다.";
        }

        var albumCover = document.getElementById('albumCover');
        if (responseData.album_cover_url && responseData.album_cover_url !== "Album cover not found") {
            albumCover.src = responseData.album_cover_url;
            albumCover.style.display = 'block';
            albumCover.alt = `Album cover of ${responseData.recommended_track}`;
        } else {
            albumCover.style.display = 'none';
            albumCover.alt = 'No album cover available';
        }
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('loading').style.display = 'none';
    });
}

window.addEventListener('load', function() {
    var start = document.getElementById('start');
    setTimeout(function() {
        start.style.opacity = '0';
        setTimeout(function() {
            start.style.display = 'none';
            document.getElementById('header').style.display = 'block';
        }, 500);
    }, 1500);
});
