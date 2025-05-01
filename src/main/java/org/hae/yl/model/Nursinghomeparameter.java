package org.hae.yl.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Nursinghomeparameter {
    private Integer id;         //机构 ID
    private String name;        //机构名称
    private String address;     //ip 地址
    private String phone;       //手机号
    private String description; //描述信息
    private Double longitude;   //经度
    private Double latitude;    //纬度
    private String level;       //星级
}
