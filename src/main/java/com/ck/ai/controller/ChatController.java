package com.ck.ai.controller;

import com.ck.ai.service.OpenAIHttpService;
import com.ck.ai.domain.ChatResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "chatGpt")
public class ChatController {
    @Autowired
    private OpenAIHttpService chatGPTClient;
    @RequestMapping(path = "/chat/question",method = RequestMethod.GET)
    @ApiOperation("获取对话结果")
    public ChatResponse predict(@RequestParam("question")String question) {
        return chatGPTClient.chat(question);
    }
}
