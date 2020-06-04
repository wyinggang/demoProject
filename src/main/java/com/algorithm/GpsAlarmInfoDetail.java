package com.algorithm;

import lombok.Data;

@Data
public class GpsAlarmInfoDetail {

       /** 报警类型 **/
       private Number alarmType;
       /** 报警类型名称 **/
       private String alarmTypeDesc;
       /** 设备号 **/
       private String imei;
       /** 设备类型 **/
       private Number isWireless;
       /** 速度 **/
       private Number speed;
       /** 经度 **/
       private String lng;
       /** 维度 **/
       private String lat;
       /** 地址 **/
       private String address;
       /** 设备绑定状态，0：解绑，1：绑定 **/
       private Number deviceBoundStatus;
       /** 车架号 **/
       private String vin;
       /** 车主 **/
       private String carOwner;
       /** 车牌号 **/
       private String licenseNumber;
       /** 公司名称 **/
       private String rootName;
       /** 门店名称 **/
       private String organizationName;
       /** 报警时间 **/
       private Number alarmTime;
       /** 开始时间 **/
       private Number startTime;
       /** 结束时间 **/
       private Number endTime;
       /** 持续时长 **/
       private Number duration;

}
