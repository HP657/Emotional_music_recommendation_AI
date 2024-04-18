import requests

def get_song_info(artist, track):
    url = f'http://musicbrainz.org/ws/2/recording?query=artist:"{artist}" AND recording:"{track}"&fmt=json'
    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        if 'recordings' in data and len(data['recordings']) > 0:
            recording_info = data['recordings'][0]
            song_title = recording_info['title']
            artist_name = recording_info['artist-credit'][0]['name']
            album_title = recording_info['releases'][0]['title'] if 'releases' in recording_info and len(recording_info['releases']) > 0 else None
            album_mbid = recording_info['releases'][0]['id'] if 'releases' in recording_info and len(recording_info['releases']) > 0 else None
            return song_title, artist_name, album_title, album_mbid
    return None

def get_album_cover(album_mbid):
    if not album_mbid:
        return None
    url = f'http://coverartarchive.org/release/{album_mbid}'
    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        if 'images' in data and len(data['images']) > 0:
            image_url = data['images'][0]['image']
            return image_url
    return None

# 노래 정보와 표지 가져오기
artist = 'ruel'
track = 'painkiller'
song_info = get_song_info(artist, track)
if song_info:
    song_title, artist_name, album_title, album_mbid = song_info
    print(f'Song: {song_title}\nArtist: {artist_name}\nAlbum: {album_title}')
    # 앨범 표지 가져오기
    album_cover_url = get_album_cover(album_mbid)
    if album_cover_url:
        print(f'Album Cover URL: {album_cover_url}')
    else:
        print('Album cover not found.')
else:
    print('Song information not found.')
