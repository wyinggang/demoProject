package com.algorithm;

import lombok.Data;

@Data
public class GpsAuthReq {

    private String appid;

    private long time;

    private String signature;
}
