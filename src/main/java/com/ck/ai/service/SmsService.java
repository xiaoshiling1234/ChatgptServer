package com.ck.ai.service;

import com.ck.ai.domain.JsonResult;

public interface SmsService {
    JsonResult sendSms(String phoneNumber, String message);
}

