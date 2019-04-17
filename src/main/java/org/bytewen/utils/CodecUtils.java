package org.bytewen.utils;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class CodecUtils {
    private CodecUtils(){}
    public static String md5Hex(String data,String salt){
        if(StringUtils.isEmpty(salt)){
            salt=data.hashCode()+"";
        }
        return DigestUtils.md5Hex(salt+DigestUtils.md5Hex(data));
    }

    public static String shaHex(String data,String salt){
        if(StringUtils.isEmpty(salt)){
            salt=data.hashCode()+"";
        }
        return DigestUtils.sha512Hex(salt+DigestUtils.sha512Hex(data));
    }
    public static String generateSalt(){
        return StringUtils.replace(UUID.randomUUID().toString(),"-","");
    }
}
