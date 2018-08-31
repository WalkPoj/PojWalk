package com.walk.service.impl;

import com.walk.pojo.CpachaUtil;
import com.walk.service.CpachaService;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class CpachaServiceimpl implements CpachaService {
    @Override
    public void generateLoginCpacha(HttpSession session , HttpServletResponse response)throws IOException{
        CpachaUtil cpachaUtil = new CpachaUtil();
        String generatorVCode = cpachaUtil.generatorVCode();
        session.setAttribute("loginCapcha",generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode,true);
        ImageIO.write(generatorRotateVCodeImage,"gif",response.getOutputStream());

    }
}
