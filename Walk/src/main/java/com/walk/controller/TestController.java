package com.walk.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class TestController {

    @RequestMapping("/index")
    public String test(Model mod){
        mod.addAttribute("index","a");
        return "fileUpload";
    }

    @Value("${Mydir.serverURI}")
    private String fileDir;

    @RequestMapping("/upload")
    public String upload( HttpServletRequest request,@RequestParam("file") MultipartFile[] files) throws IOException{
        System.out.println("进来了");

        List<String> list = new ArrayList<String>();
        // 获得项目的路径
        ServletContext sc = request.getSession().getServletContext();
        // 上传位置
        String path = fileDir; // 设定文件保存的目录
        File f = new File(path);
        if (!f.exists())
            f.mkdirs();

        for (int i = 0; i < files.length; i++) {
            // 获得原始文件名
            String fileName = files[i].getOriginalFilename();
            System.out.println("原始文件名:" + fileName);
            // 新文件名
            String newFileName = UUID.randomUUID() + fileName;
            if (!files[i].isEmpty()) {
                try {
                    FileOutputStream fos = new FileOutputStream(path
                            + newFileName);
                    InputStream in = files[i].getInputStream();
                    int b = 0;
                    while ((b = in.read()) != -1) {
                        fos.write(b);
                    }
                    fos.close();
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("上传图片到:" + path + newFileName);
            list.add(path + newFileName);

        }
        return "index";
    }
    /**
     * 文件下载功能
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/down")
    public void down(String fname,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //模拟文件，myfile.txt为需要下载的文件
        String fileName =fileDir+fname;
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
        //假如以中文名下载的话
        String filename = "下载文件.txt";
        //转码，免得文件名中文乱码
        filename = URLEncoder.encode(filename,"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
    }

}
