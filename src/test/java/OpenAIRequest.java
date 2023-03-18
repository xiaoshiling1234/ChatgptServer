import org.springframework.http.*;
import org.springframework.web.client.*;

public class OpenAIRequest {

    public static void main(String[] args) {
        String url = "https://api.openai.com/v1/chat/completions";

        // 构造请求头部信息
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer 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");

        // 构造请求体
        String model = "gpt-3.5-turbo";
        String message = "{\"role\": \"user\", \"content\": \"Say this is a test!\"}";
        String data = String.format("{\"model\": \"%s\", \"messages\": [%s], \"temperature\": 0.7}", model, message);

        // 构造HTTP请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(data, headers);

        // 创建RestTemplate实例并发送HTTP请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

        // 读取响应数据
        String response = responseEntity.getBody();

        // 打印响应结果
        System.out.println(response);
    }
}
