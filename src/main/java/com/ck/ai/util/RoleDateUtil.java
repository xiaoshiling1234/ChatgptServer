package com.ck.ai.util;

import com.ck.ai.domain.enums.RoleEnum;

import java.util.Calendar;
import java.util.Date;

public class RoleDateUtil {
    public static Date calculateExpireDate(RoleEnum type) {
        if (type == RoleEnum.PERMANENT) {
            return null; // 永久卡没有过期时间
        } else {
            // 计算过期时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, type.getDays());
            return calendar.getTime();
        }
    }
}
