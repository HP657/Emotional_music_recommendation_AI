import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RunPythonScript {
    public static void main(String[] args) {
        try {
            // Python 스크립트 파일 경로
            String pythonScriptPath = "hello.py";

            // 전달할 변수
            String variable = "Hello, Python!";

            // 인수(argument)로 전달할 값들을 리스트에 추가
            List<String> command = new ArrayList<>();
            command.add("python");
            command.add(pythonScriptPath);
            command.add(variable);

            // 프로세스 빌더 생성
            ProcessBuilder pb = new ProcessBuilder(command);

            // 프로세스 실행
            Process p = pb.start();

            // 프로세스의 출력을 읽어들이기 위한 BufferedReader 생성
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
