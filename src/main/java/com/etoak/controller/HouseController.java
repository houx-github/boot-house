package com.etoak.controller;

import com.etoak.bean.House;
import com.etoak.bean.HouseVo;
import com.etoak.bean.Page;
import com.etoak.service.HouseService;
import com.etoak.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/house")
public class HouseController {
    @Value("${upload.dir}")
   private String uploadDir;
    @Value("${upload.savePathPrefix}")
    private String savePathPrefix;
@Autowired
    HouseService houseService;


    @RequestMapping("/toAdd")
    public String toAdd(){
        return "house/add";
    }

    /**
     * 后端校验方法中使用
     * @param file
     * @param house
     * @return
     * @throws Exception
     */
  /*  @PostMapping("/add")
    public  String add(@RequestParam("file")MultipartFile file, @Valid House house, BindingResult bindingResult) throws Exception {
//对参数进行校验
        List<ObjectError> allErrors=bindingResult.getAllErrors();
        if (CollectionUtils.isNotEmpty(allErrors)){
            StringBuffer msgBuffer=new StringBuffer();
            for (ObjectError objectError:allErrors){
                String message=objectError.getDefaultMessage();
                msgBuffer.append(message).append(";");
            }
            throw new ParamException("参数校验失败:"+msgBuffer.toString());
        }

        //上传文件
        String originalFilename = file.getOriginalFilename();
        String prefix = UUID.randomUUID().toString().replaceAll("-", "");
        //新文件名
        String newFilename = prefix  + "_" + originalFilename;
        File destFile=new File(this.uploadDir,newFilename);
        file.transferTo(destFile);

        house.setPic(this.savePathPrefix+newFilename);
        houseService.addHouse(house);
        return "redirect:/house/toAdd";
    }*/

    /**
     * 后端校验引用类
     * @param file
     * @param house
     * @return
     * @throws Exception
     */
  @PostMapping("/add")
  public  String add(@RequestParam("file")MultipartFile file,  House house) throws Exception {
//对参数进行校验
      ValidationUtil.validate(house);

      //上传文件
      String originalFilename = file.getOriginalFilename();
      String prefix = UUID.randomUUID().toString().replaceAll("-", "");
      //新文件名
      String newFilename = prefix  + "_" + originalFilename;
      File destFile=new File(this.uploadDir,newFilename);
      file.transferTo(destFile);

      house.setPic(this.savePathPrefix+newFilename);
      houseService.addHouse(house);
      return "redirect:/house/toAdd";
  }

    @GetMapping(value = "/list",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Page<HouseVo> queryList(@RequestParam(required = false,defaultValue = "1") int pageNum,
                                   @RequestParam(required = false,defaultValue = "10") int pageSize,
                                   HouseVo houseVo,@RequestParam(value = "rentalArr[]",required = false) String[] rentalList){
log.info("pageNum - {},pageSize - {},hoseVo - {},rental-{}",pageNum,pageSize,houseVo,rentalList);

return houseService.queryList(pageNum,pageSize,houseVo,rentalList);
    }
    //list页面
    @GetMapping("/toList")
    public String toList(){
      return "house/list";
    }

    @PutMapping("/update")
    public String update(House house){
      log.info("house -{}",house);
      houseService.updateHouse(house);
      return "redirect:/house/toList";
    }
    @DeleteMapping("/{id}")
    public String deleteHouse(@PathVariable("id") int id){
      log.info("delete id -{}",id);
      houseService.deleteById(id);
      return  "redirect:/house/toList";
    }
}
