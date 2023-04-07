package com.ck.ai.controller;

import com.ck.ai.domain.JsonResult;
import com.ck.ai.domain.entity.User;
import com.ck.ai.domain.enums.RoleEnum;
import com.ck.ai.service.OpenAIHttpService;
import com.ck.ai.domain.ChatResponse;
import com.ck.ai.service.UserService;
import com.ck.ai.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

import static com.ck.ai.domain.JsonResult.OK;

@RestController
@Api(tags = "chatGpt")
public class ChatController {
    @Autowired
    private OpenAIHttpService chatGPTClient;
    @Autowired
    UserService userService;
    @RequestMapping(path = "/chat/question",method = RequestMethod.GET)
    @RequiresPermissions(value = {"perm:chat", "perm:admin"}, logical = Logical.OR)
    @ApiOperation("获取对话结果")
    public JsonResult predict(
            @ApiParam(name = "token", value = "token", required = true)
            @RequestHeader(name = "token") String token,
            @RequestParam("question")String question) {
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        String username = JWTUtil.getUsername(principal);
        User user = userService.getUserByUsername(username);
        if (user.getRole()== RoleEnum.TEMP&&user.getFreeChatTimes()>0){
            ChatResponse chatResponse = chatGPTClient.chat(question);
            user.setFreeChatTimes(user.getFreeChatTimes()-1);
            userService.updateUserByUsername(user);
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("chatResponse",chatResponse);
            resultMap.put("FreeChatTimes",user.getFreeChatTimes());
            return JsonResult.OK(resultMap);
        }
        return JsonResult.Fail("No remaining call times");
    }
}
