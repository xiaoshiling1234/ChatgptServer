package com.ck.ai.service;

import com.ck.ai.config.TencentSmsConfig;
import com.ck.ai.domain.JsonResult;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TencentSmsService implements SmsService {
    private final SmsClient client;
    @Autowired
    TencentSmsConfig tencentSmsConfig;

    @Autowired
    public TencentSmsService(TencentSmsConfig tencentSmsConfig) {
        Credential cred = new Credential(tencentSmsConfig.getSecretId(), tencentSmsConfig.getSecretKey());
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(tencentSmsConfig.getEndpoint());
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        this.client = new SmsClient(cred, "ap-beijing", clientProfile);
    }

    @Override
    public JsonResult sendSms(String phoneNumber, String message) {
        SendSmsRequest req = new SendSmsRequest();
        String[] phoneNumberSet1 = {phoneNumber};
        req.setPhoneNumberSet(phoneNumberSet1);

        req.setSmsSdkAppId(tencentSmsConfig.getSms().getSmsSdkAppId());
        req.setSignName(tencentSmsConfig.getSms().getSignName());
        req.setTemplateId(tencentSmsConfig.getSms().getTemplateId());

        String[] templateParamSet1 = {message};
        req.setTemplateParamSet(templateParamSet1);
        try {
            SendSmsResponse resp = client.SendSms(req);
            return JsonResult.OK(resp);
        } catch (TencentCloudSDKException e) {
            return JsonResult.Fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.toString());
        }
    }

    public static void main(String[] args) {

    }
}

