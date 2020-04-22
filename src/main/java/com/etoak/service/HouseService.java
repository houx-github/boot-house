package com.etoak.service;

import com.etoak.bean.House;
import com.etoak.bean.HouseVo;
import com.etoak.bean.Page;

import java.util.List;

public interface HouseService {
    int addHouse(House house);

    /**
     * 条件列表查询
     * @param pageNum
     * @param pageSize
     * @param houseVo
     * @return
     */
    Page<HouseVo> queryList(int pageNum,int pageSize,HouseVo houseVo);
}
