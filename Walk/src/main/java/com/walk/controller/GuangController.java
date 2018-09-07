package com.walk.controller;

import com.walk.pojo.InsertScenry;
import com.walk.pojo.Mark;
import com.walk.pojo.Scenery;
import com.walk.service.SceneryService;
import com.walk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("center")
public class GuangController {

    @Autowired
    private UserService uve;

    @Autowired
    private SceneryService sve;

    @RequestMapping("/Guang")
    public String Guang(int uid,int mid,HttpServletRequest request){
        request.setAttribute("uid",uid);
        request.setAttribute("mid",mid);
        System.out.println(uid);
        System.out.println(mid);
        return "center/Guang";
    }

    @RequestMapping("DeletePassword")
    @ResponseBody
    public String DeletePassword(String jpsw,String xpsw,String npsw,int uid){
        String psw = uve.ispsw(uid);
        System.out.println("dv"+psw);
        if (!jpsw.equals(psw)){
            System.out.println(jpsw);
            return "errorpsw";
        }else if (!xpsw.equals(npsw)){
            return "different";
        }
       boolean orw = uve.updatepsw(npsw,uid);
        if (orw==true){
            return "success";
        }else {
            return "failure";
        }
    }

    @RequestMapping("/dataGrid")
    @ResponseBody
    public Map<String,Object> dataGrid2(Scenery scenery, int page, int rows, String order, String sort, HttpSession session){
        Mark mark =(Mark)session.getAttribute("mark");
        scenery.setM_id(mark.getM_id());
        System.out.println("id"+mark.getM_id());
        System.out.println("title"+scenery.getS_title());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", this.sve.getSceneryCount(scenery));
        map.put("rows", this.sve.findAllScenery(scenery,page,rows,sort,order));
        return map;
    }

    @RequestMapping("/xinzeng")
    public String xinzeng(){
        System.out.println("进来玩啊");
        return "redirect:../scsc.html";
    }
    @RequestMapping("/filesUpload")
    @ResponseBody
    //requestParam要写才知道是前台的那个数组
    public String filesUpload(@RequestParam(name="files") MultipartFile[] files,
                              @RequestParam(name="filess") MultipartFile filess,
                              @RequestParam(name="myfiles") MultipartFile[] myfiles,
                              InsertScenry insertScenry, HttpServletRequest request) {
        InsertScenry is = insertScenry;
        Mark mark =(Mark)request.getSession().getAttribute("mark");
        System.out.println("商家id"+mark.getM_id());
        System.out.println("标题"+insertScenry.getS_title());
        System.out.println("详情"+insertScenry.getS_txt());
        System.out.println("价格"+insertScenry.getS_price());
        System.out.println("原价"+insertScenry.getS_price_yuan());
        List<String> list = new ArrayList<>();
       // List<String> lists = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        int s = (int)(Math.random()*100000)+1;
        String mz = "beiji_"+df.format(new Date())+s;
        String dz="C:/tomcat_media/webapps/walk_img/"+mz;
        String fm ="C:/tomcat_media/webapps/walk_fmImg";
        is.setM_id(mark.getM_id());
        is.setS_img(dz);
        is.setS_fmImg("walk_fmImg/"+filess.getOriginalFilename());
        boolean orw = sve.insertScenry(is);
        if (orw==false){
            return "shib";}else{
        int isf = 0;//用来判断上传到哪个路径
        if (filess!=null){
            isf=2;
            list = saveFile(request,filess,list,s,isf,fm);
        }
        if (files.length<6 && files.length>0){
            if (myfiles.length>0){
               if (files != null && files.length > 0) {
                   isf = 0;
                   for (int i = 0; i < files.length; i++) {
                       MultipartFile file = files[i];
                       System.out.println("wjm"+file);
                       // 保存文件
                       list = saveFile(request, file, list,s,isf,dz);
                   }
               }
                //写着测试，删了就可以
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("集合里面的数据" + list.get(i));
                }
            }else{
                return "nomin";
            }
        }else{
            return "nomax";
        }
        if (myfiles.length>0){
            if (files != null && files.length > 0){
                if(myfiles != null && myfiles.length>0){
                    isf = 1;
                    for (int i = 0; i <myfiles.length ; i++) {
                    MultipartFile myfile = myfiles[i];
                    list = saveFile(request,myfile,list,s,isf,dz);
                    }
                }
            }else{
                return "nomax";
            }
        }else{
            return "nomin";
        }

        return "somss";
        }
    }

    private List<String> saveFile(HttpServletRequest request,
                                  MultipartFile file, List<String> list,int s,int isf,String path) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                // )
//                String filePaths = request.getSession().getServletContext()
//                        .getRealPath("/")
//                        + "upload/" + file.getOriginalFilename();
//                System.out.println("获取本地地址"+filePaths);


                String min = file.getOriginalFilename();
                String filePath="";
                if(isf==0){
                    filePath = path+"/lb/"+min;
                }else if(isf==1) {
                    filePath = path+"/xq/"+min;
                }else if(isf==2){
                    filePath = path+"/"+min;
                }
                System.out.println("地址"+filePath);
                list.add(file.getOriginalFilename());
                System.out.println("图片"+file.getOriginalFilename());
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                // 转存文件
                file.transferTo(saveDir);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
