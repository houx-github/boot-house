package com.etoak.bean;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class House {
    private Integer id;
    @NotNull(message = "省份必填")
    private Integer province;
    @NotNull(message = "市必填")
    private Integer city;
    @NotNull(message = "所在区必填")
    private Integer area;
    //所在区县名称,
    private String areaName;
    //租赁方式
    @NotNull(message = "租赁方式必填")
    @NotEmpty(message = "租赁方式不能为空")
    private String rentMode;
    //朝向
    private String orientation;
    private String houseType;
    //租金
    @NotNull(message = "租金必填")
    @Max(value = 100000,message = "租金不能超过十万")
    @Min(value = 100,message = "租金不能少于100")
    private Integer rental;
    @Size(min=1,max = 10,message = "地址介于1-10之间")
    @NotNull(message = "地址不为空")
    private String address;
    private String pic;
    private String publishTime;
}
