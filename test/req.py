import requests

def search_tracks_by_emotion(emotion, api_key):
    url = f'http://ws.audioscrobbler.com/2.0/?method=track.search&track={emotion}&api_key={api_key}&format=json'
    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        return data
    else:
        print("Failed to fetch data from Last.fm API.")
        return None

def recommend_tracks_by_emotion(emotion):
    api_key = 'f690b01ffe76a69f0cea990c04dbb910'
    result = search_tracks_by_emotion(emotion, api_key)
    if result:
        tracks = result['results']['trackmatches']['track']
        return tracks
    else:
        return None

# 사용자가 입력한 감정
emotion = input("Enter an emotion (e.g., happiness, anger, sadness): ")
recommended_tracks = recommend_tracks_by_emotion(emotion)
if recommended_tracks:
    print(f"Recommended tracks for {emotion}:")
    for track in recommended_tracks:
        print(f"- {track['name']} by {track['artist']}")
else:
    print("An error occurred while fetching data.")
