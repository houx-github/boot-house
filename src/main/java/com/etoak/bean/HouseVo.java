package com.etoak.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class HouseVo extends House {
    private String rentModeName;
    private String houseTypeName;
    private String orientationName;
    @JsonIgnore//不将此字段返回
    private String[] houseTypeList;
    @JsonIgnore
    private List<String> orientationList;
    @JsonIgnore
    private List<Map<String,Integer>> rentalList;

}
