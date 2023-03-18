package com.ck.ai.Service;


import com.ck.ai.bean.ChatRequest;
import com.ck.ai.bean.ChatResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Slf4j
@Service
public class OpenAIHttpService {
    @Value("${open.ai.model}")
    private String openAiModel;
    @Value("${open.ai.url}")
    private String url;
    @Value("${open.ai.key}")
    private String OPENAI_API_KEY;

    public ChatResponse chat(String content) {
        try {
            RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(OPENAI_API_KEY);

            ChatRequest request = new ChatRequest(
                    openAiModel,
                    Collections.singletonList(new ChatRequest.Message("user", content)),
                    0.7f
            );
            String requestBody = new Gson().toJson(request);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new org.springframework.http.HttpEntity<>(requestBody, headers),
                    String.class
            );

            String responseJson = responseEntity.getBody();
            return ChatResponse.fromJson(responseJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
