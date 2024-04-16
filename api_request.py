import requests

# Flask 서버의 주소
server_url = ' https://feelow-ai.run.goorm.site/classify'
# 보낼 데이터
data = {'sentence': '안녕하세요, 감정 분석을 해주세요.'}  # 분석할 문장을 입력합니다

# POST 요청 보내기
response = requests.post(server_url, json=data)

# 응답 확인
if response.status_code == 200:
    result = response.json()
    print("예측된 감정:", result['emotion'])
else:
    print("서버 응답 실패:", response.status_code)
