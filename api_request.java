import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Main {
    public static void main(String[] args) {
        // HttpClient 생성
        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        // Flask 서버의 주소
        String serverUrl = "https://feelow-ai.run.goorm.site/classify";
        // 보낼 데이터
        String jsonData = "{\"sentence\": \"안녕하세요, 감정 분석을 해주세요.\"}";

        // POST 요청 설정
        HttpPost httpPost = new HttpPost(serverUrl);
        httpPost.addHeader("Content-Type", "application/json");

        // JSON 데이터를 요청 엔터티로 설정
        StringEntity requestEntity = new StringEntity(jsonData, ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);

        try {
            // 요청 보내기
            CloseableHttpResponse response = httpClient.execute(httpPost);
            
            // 응답 확인
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // 응답 본문 읽기
                    String responseString = EntityUtils.toString(entity);
                    System.out.println("예측된 감정: " + responseString);
                }
            } else {
                System.out.println("서버 응답 실패: " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // HttpClient 닫기
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
