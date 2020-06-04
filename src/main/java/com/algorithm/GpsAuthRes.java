package com.algorithm;

import lombok.Data;

@Data
public class GpsAuthRes {

    private int code;

    private int expiresIn;

    private String accessToken;

    private String result;
}
