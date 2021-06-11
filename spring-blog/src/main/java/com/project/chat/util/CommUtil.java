package com.project.chat.util;

import java.util.UUID;

public class CommUtil {
	//uuid생성 
    public static String getUuid() { 
        return UUID.randomUUID().toString().replaceAll("-", ""); 
    }

}
