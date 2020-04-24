package com.etoak.mapper;

import com.etoak.bean.House;
import com.etoak.bean.HouseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    /**
     * 添加房源信息
     * @param house
     * @return
     */
    int addHouse(House house);

    /**
     * 房源列表查询
     * @param houseVo
     * @return
     */
    List<HouseVo> queryList(HouseVo houseVo);

    int updateHouse(House house);

    int deleteById(@Param("id") int id);
}
