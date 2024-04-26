function saveEmotion(event) {
    document.getElementById('loading').style.display = 'block';
    let emotionText = document.getElementById('emotionInput').value;
    fetch('https://feelow-ai.run.goorm.site/recommend_song', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ sentence: emotionText })
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('loading').style.display = 'none';
        console.log(data);
        document.getElementById('emotionDisplay').textContent = `감정: ${data.emotion}`;
        if (data.recommended_track && data.recommended_artist) {
            document.getElementById('trackInfo').textContent = `추천 곡: ${data.recommended_track} by ${data.recommended_artist}`;
            var albumCover = document.getElementById('albumCover');
            if (data.album_cover_url && data.album_cover_url !== "Album cover not found") {
                albumCover.src = data.album_cover_url;
                albumCover.style.display = 'block';
                albumCover.alt = `Album cover of ${data.recommended_track}`;
            } else {
                albumCover.style.display = 'none';
                albumCover.alt = 'No album cover available';
            }
        } else {
            document.getElementById('trackInfo').textContent = "추천 곡을 찾을 수 없습니다.";
            document.getElementById('albumCover').style.display = 'none';
        }
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('loading').style.display = 'none';
    });
}
