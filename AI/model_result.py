import sys
from konlpy.tag import Okt
from gensim.models import Word2Vec
from sklearn.svm import SVC
from joblib import load

# Okt 형태소 분석기 초기화
okt = Okt()

# 형태소 분석 함수 정의
def tokenize(text):
    return okt.morphs(text)

# 주어진 문장
sentence = "오늘 기분 쨰짐"  # 커맨드 라인에서 입력값 받기

# 형태소로 분할
tokenized_sentence = tokenize(sentence)

# Word2Vec 모델 불러오기
model = Word2Vec.load("word2vec_model.joblib")

# 형태소를 Word2Vec 임베딩으로 변환하는 함수 정의
def get_embedding(model, tokens):
    embeddings = [model.wv[token] for token in tokens if token in model.wv.key_to_index]
    if embeddings:
        return sum(embeddings) / len(embeddings)
    else:
        return []

# 문장을 Word2Vec 임베딩으로 변환
embedding = get_embedding(model, tokenized_sentence)

# SVM 모델 불러오기
svm_model = load("svm_model.joblib")

# 감정 분류
emotion = svm_model.predict([embedding])[0]

print("예측된 감정:", emotion)
