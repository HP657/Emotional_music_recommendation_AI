package com.example.feelow.service;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

@Service
public class EmotionService {

    public String processEmotion(String sentence) {
        try {
            // 파이썬 스크립트의 경로 설정
            String pythonScriptPath = Paths.get("AI", "model_result.py").toString();

            // 파이썬 실행 명령 구성
            ProcessBuilder builder = new ProcessBuilder("python", "../../../../resources/AI/model_result.py", sentence);
            builder.redirectErrorStream(true); // 표준 오류를 표준 출력에 병합

            // 프로세스 시작
            Process process = builder.start();

            // 결과 읽기
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // 프로세스 종료 대기
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("파이썬 스크립트 실행 중 오류 발생, 종료 코드: " + exitCode);
            }

            // 파이썬 스크립트로부터 받은 출력 반환
            return output.toString().trim(); // 출력 문자열의 앞뒤 공백 제거
        } catch (Exception e) {
            e.printStackTrace();
            return "파이썬 스크립트 실행 중 오류 발생: " + e.getMessage();
        }
    }
}
