package com.ck.ai.domain;

import com.google.gson.Gson;

public class ChatResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private Usage usage;
    private Choice[] choices;

    public static ChatResponse fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ChatResponse.class);
    }

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public long getCreated() {
        return created;
    }

    public String getModel() {
        return model;
    }

    public Usage getUsage() {
        return usage;
    }

    public Choice[] getChoices() {
        return choices;
    }

    public static class Usage {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;

        public int getPrompt_tokens() {
            return prompt_tokens;
        }

        public int getCompletion_tokens() {
            return completion_tokens;
        }

        public int getTotal_tokens() {
            return total_tokens;
        }
    }

    public static class Choice {
        private Message message;
        private String finish_reason;
        private int index;

        public Message getMessage() {
            return message;
        }

        public String getFinish_reason() {
            return finish_reason;
        }

        public int getIndex() {
            return index;
        }
    }

    public static class Message {
        private String role;
        private String content;

        public String getRole() {
            return role;
        }

        public String getContent() {
            return content;
        }
    }

    public static void main(String[] args) {
        String json = "{\"id\":\"chatcmpl-6vS823jeuRcELjgRWhpHXMsTiw5LG\",\"object\":\"chat.completion\",\"created\":1679150842,\"model\":\"gpt-3.5-turbo-0301\",\"usage\":{\"prompt_tokens\":13,\"completion_tokens\":6,\"total_tokens\":19},\"choices\":[{\"message\":{\"role\":\"assistant\",\"content\":\"\\n\\nThis is a test!\"},\"finish_reason\":\"stop\",\"index\":0}]}";

        ChatResponse response = ChatResponse.fromJson(json);
        System.out.println(response.getChoices()[0].getMessage().getContent());
    }
}
