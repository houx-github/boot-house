package com.etoak.service;

import com.etoak.bean.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaService {
   List<Area> queryByPid(int pid);
   Area queryById(@Param("id") int id);
}
