package com.ck.ai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tencent")
public class TencentSmsConfig {
    private String endpoint;
    private String secretId;
    private String secretKey;
    private SmsConfig sms;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public SmsConfig getSms() {
        return sms;
    }

    public void setSms(SmsConfig sms) {
        this.sms = sms;
    }

    public static class SmsConfig {
        private String smsSdkAppId;
        private String signName;
        private String templateId;

        public String getSmsSdkAppId() {
            return smsSdkAppId;
        }

        public void setSmsSdkAppId(String smsSdkAppId) {
            this.smsSdkAppId = smsSdkAppId;
        }

        public String getSignName() {
            return signName;
        }

        public void setSignName(String signName) {
            this.signName = signName;
        }

        public String getTemplateId() {
            return templateId;
        }

        public void setTemplateId(String templateId) {
            this.templateId = templateId;
        }
    }
}
