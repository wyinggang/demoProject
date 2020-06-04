package com.algorithm;

import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

public class MD5Util {

    public static String md5(String dataStr){
       return DigestUtils.md5DigestAsHex(dataStr.getBytes());
    }

    public static void main(String[] args) {


    }
}
