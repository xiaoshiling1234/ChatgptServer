package com.ck.ai.controller;

import com.ck.ai.service.OpenAIHttpService;
import com.ck.ai.domain.ChatResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "chatGpt")
public class ChatController {
    @Autowired
    private OpenAIHttpService chatGPTClient;
    @RequestMapping(path = "/chat/question",method = RequestMethod.GET)
    @RequiresPermissions(value = {"perm:chat", "perm:admin"}, logical = Logical.OR)
    @ApiOperation("获取对话结果")
    public ChatResponse predict(
            @ApiParam(name = "token", value = "token", required = true)
            @RequestHeader(name = "token") String token,
            @RequestParam("question")String question) {
        return chatGPTClient.chat(question);
    }
}
