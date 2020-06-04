package com.algorithm;

import lombok.Data;
import java.util.List;

@Data
public class GpsAlarmInfo {

    /** 错误码，0：成功，其他：失败  **/
    private Number code;

    /** 错误信息  **/
    private String result;

    /** 数据  **/
    List<GpsAlarmInfoDetail> data;

}
