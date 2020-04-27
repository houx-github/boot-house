package com.etoak.controller;

import com.etoak.commons.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller

public class CodeController {
    @GetMapping("/code")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //创建验证码对象
        VerifyCode code = new VerifyCode();
        //获取图片,写道前端页面
        BufferedImage image = code.getImage();
        //图片表达式结果,保存到本次session中
        int result = code.getResult();
        HttpSession session = request.getSession();
        session.setAttribute("code",result);

        //向前端写图片禁止缓存
        response.setHeader("Pragma","No-Cache");
        response.setHeader("Cache-Control","No-Cache");
        response.setDateHeader("Expires",0L);
        response.setContentType("image/jpeg");
        ImageIO.write(image,"JPEG",response.getOutputStream());
    }

}
