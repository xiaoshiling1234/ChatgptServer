package com.ck.ai.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class ChatRequest {
    private String model;
    private List<Message> messages;
    private float temperature;
    @Data
    @AllArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }
}

