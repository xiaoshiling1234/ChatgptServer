package com.ck.ai.controller;

import com.ck.ai.Server.OpenAIHttpClient;
import com.ck.ai.bean.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "chatGpt")
public class ChatController {
    @Autowired
    private OpenAIHttpClient chatGPTClient;
    @RequestMapping(path = "/chat/question",method = RequestMethod.GET)
    @ApiOperation("获取对话结果")
    public ResultResponse predict(@RequestParam("question")String question) {
        if(StringUtils.isBlank(question)){
            return new ResultResponse("Please Input",HttpStatus.LENGTH_REQUIRED.value());
        }
        return new ResultResponse(chatGPTClient.chat(question), HttpStatus.OK.value());
    }
}
