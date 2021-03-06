package com.etoak.controller;

import com.etoak.bean.Area;
import com.etoak.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/area")
@Slf4j
@Api(tags = "区域查询字典")
public class AreaController {
    @Autowired
    AreaService areaService;
    /**
     * 地区查询接口
     * pid为0表示查询省列表
     */
    @GetMapping("/queryByPid")
    @ApiOperation(value = "查询地区根据父id",notes = "查询地区根据父id")
    @ApiImplicitParam(value = "父id",name = "pid",
            required = false,defaultValue = "0",dataType = "int",paramType = "query")
    public List<Area> queryByPid(@RequestParam(required = false,defaultValue = "0")
                                 int pid){
       log.info("pid - {}",pid);
        return  areaService.queryByPid(pid);

    }
}
