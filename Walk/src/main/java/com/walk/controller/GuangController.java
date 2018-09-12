package com.walk.controller;

import com.walk.pojo.Echarts;
import com.walk.pojo.InsertScenry;
import com.walk.pojo.Mark;
import com.walk.pojo.Scenery;
import com.walk.service.MorderService;
import com.walk.service.SceneryService;
import com.walk.service.UserService;
import com.walk.util.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("center")
public class GuangController {

    @Autowired
    private UserService uve;

    @Autowired
    private SceneryService sve;

    @Autowired
    private MorderService mve;

    @RequestMapping("/Guang")
    public String Guang(int uid, int mid, HttpServletRequest request) {
        request.setAttribute("uid", uid);
        request.setAttribute("mid", mid);
        System.out.println(uid);
        System.out.println(mid);
        return "center/Guang";
    }

    @RequestMapping("DeletePassword")
    @ResponseBody
    public String DeletePassword(String jpsw, String xpsw, String npsw, int uid) {
        String psw = uve.ispsw(uid);
        System.out.println("dv" + psw);
        if (!jpsw.equals(psw)) {
            System.out.println(jpsw);
            return "errorpsw";
        } else if (!xpsw.equals(npsw)) {
            return "different";
        }
        boolean orw = uve.updatepsw(npsw, uid);
        if (orw == true) {
            return "success";
        } else {
            return "failure";
        }
    }

    @RequestMapping("/dataGrid")
    @ResponseBody
    public Map<String, Object> dataGrid1(Scenery scenery, int page, int rows, String order, String sort, HttpSession session) {
        Mark mark = (Mark) session.getAttribute("mark");
        scenery.setM_id(mark.getM_id());
        System.out.println("id" + mark.getM_id());
        System.out.println(page+"title" + scenery.getS_title());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", this.sve.getSceneryCount(scenery));
        map.put("rows", this.sve.findAllScenery(scenery, page, rows, sort, order));
        return map;
    }

    @RequestMapping("/dataGrid2")
    @ResponseBody
    public Map<String,Object> dataGrid2(@RequestParam Map<String,Object> maps, HttpSession session){
        Mark mark = (Mark) session.getAttribute("mark");
        int m_id = mark.getM_id();
        System.out.println("商家id"+m_id);
        maps.put("m_id",m_id);
        String page = (String) maps.get("page");
        String rows = (String)maps.get("rows");
        System.out.println(page+"sck"+rows);
        int ss = Integer.valueOf(page);
        int cc = Integer.valueOf(rows);
        maps.put("pages",ss-1);
        maps.put("rowss",cc);
        System.out.println("sort");
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(" this.mve.findOrderCount(maps)"+ this.mve.findOrderCount(maps));
        map.put("total",this.mve.findOrderCount(maps));
        System.out.println("map"+map.get("total"));
        map.put("rows",this.mve.selectOrder(maps));

        return map;
    }

    @RequestMapping("/xinzeng")
    public String xinzeng() {
        System.out.println("进来玩啊");
        return "redirect:../scsc.html";
    }

    @RequestMapping("bianji")
    public String bianji(int s_id, HttpSession session, HttpServletRequest request) {
        System.out.println("编辑id" + s_id);
        List<String> list = new ArrayList<>();
        list = getAllFile("C:/tomcat_media/webapps/walk_img/beiji_2018090737270", true);
        for (String name : list) {
            System.out.println("wj" + name);
        }
        getFile("C:/tomcat_media/webapps/walk_img/beiji_2018090737270",0);
        Scenery scenery = this.sve.selectnoeScenery(s_id);
        request.getSession().setAttribute("s_id",s_id);
        request.getSession().setAttribute("s_fmImg",scenery.getS_fmImg());
        request.getSession().setAttribute("s_img",scenery.getS_img());
        request.setAttribute("scenery", scenery);
        return "center/updateSy";
    }


    /*
     * 函数名：getFile
     * 作用：使用递归，输出指定文件夹内的所有文件
     * 参数：path：文件夹路径   deep：表示文件的层次深度，控制前置空格的个数
     * 前置空格缩进，显示文件层次结构
     */
    private static void getFile(String path,int deep){
        // 获得指定文件对象
        File file = new File(path);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();

        for(int i=0;i<array.length;i++)
        {
            if(array[i].isFile())//如果是文件
            {
                for (int j = 0; j < deep; j++)//输出前置空格
                    System.out.print(" ");
                // 只输出文件名字
                System.out.println( array[i].getName());
                // 输出当前文件的完整路径
                // System.out.println("#####" + array[i]);
                // 同样输出当前文件的完整路径   大家可以去掉注释 测试一下
                // System.out.println(array[i].getPath());
            }
            else if(array[i].isDirectory())//如果是文件夹
            {
                for (int j = 0; j < deep; j++)//输出前置空格
                    System.out.print(" ");

                System.out.println( array[i].getName());
                //System.out.println(array[i].getPath());
                //文件夹需要调用递归 ，深度+1
                getFile(array[i].getPath(),deep+1);
            }
        }
    }

    public static List<String> getAllFile(String directoryPath, boolean isAddDirectory) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if (isAddDirectory) {
                    list.add(file.getAbsolutePath());
                }
                list.addAll(getAllFile(file.getAbsolutePath(), isAddDirectory));
            } else {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }

    @RequestMapping("/syupdele")
    @ResponseBody
    public String syupdele(@RequestParam(name = "files") MultipartFile[] files,
                           @RequestParam(name = "filess") MultipartFile filess,
                           @RequestParam(name = "myfiles") MultipartFile[] myfiles,
                           InsertScenry insertScenry, HttpServletRequest request) {
        InsertScenry is = insertScenry;
        List<String> list = new ArrayList<>();
        String fm = "walk_fmImg";
        int s_id =(int)request.getSession().getAttribute("s_id");
        String s_fmImg =(String)request.getSession().getAttribute("s_fmImg");
        String s_img =(String)request.getSession().getAttribute("s_img");
        String dz = s_img;
        is.setS_id(s_id);
        System.out.println("按错是删除是AV和"+s_id);
        if (filess.getSize()>0) {
            String f1 = filess.getOriginalFilename();
            System.out.println("f1"+f1);
            fm+="/"+f1;
            insertScenry.setS_fmImg(fm);
            fm ="C:/tomcat_media/webapps/walk_fmImg";
        }else{
            insertScenry.setS_fmImg(s_fmImg);
        }
        boolean orw = sve.updateScenery(is);
        if (orw == false) {
            return "shib";
        } else {
            int isf = 0;//用来判断上传到哪个路径
            if (filess != null) {
                isf = 2;
                list = xiugaiFile(request, filess, list, isf, fm);
            }
            if (files.length < 6 && files.length > 0) {
                if (myfiles.length > 0) {
                    if (files != null && files[0].getSize() > 0) {
                        isf = 0;
                        for (int i = 0; i < files.length; i++) {
                            MultipartFile file = files[i];
                            System.out.println("wjm" + file);
                            // 保存文件
                            list = xiugaiFile(request, file, list, isf, dz);
                        }
                    }
                    //写着测试，删了就可以
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("集合里面的数据" + list.get(i));
                    }
                } else {
                    return "nomin";
                }
            } else {
                return "nomax";
            }
            if (myfiles[0].getSize() > 0) {
                if (files != null && files[0].getSize() > 0) {
                    if (myfiles != null && myfiles.length > 0) {
                        isf = 1;
                        for (int i = 0; i < myfiles.length; i++) {
                            MultipartFile myfile = myfiles[i];
                            list = xiugaiFile(request, myfile, list, isf, dz);
                        }
                    }
                } else {
                    return "nomax";
                }
            } else {
                return "nomin";
            }
        }
        return "somss";
    }

    private List<String> xiugaiFile(HttpServletRequest request,
                                    MultipartFile file, List<String> list, int isf, String path) {
        if (!file.isEmpty()) {//判断文件是否为空
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                // )
//                String filePaths = request.getSession().getServletContext()
//                        .getRealPath("/")
//                        + "upload/" + file.getOriginalFilename();
//                System.out.println("获取本地地址"+filePaths);


                String min = file.getOriginalFilename();
                String filePath = "";
                if (isf == 0) {
                    filePath = path + "/lb/" + min;
                } else if (isf == 1) {
                    filePath = path + "/xq/" + min;
                } else if (isf == 2) {
                    filePath = path + "/" + min;
                }
                list.add(file.getOriginalFilename());
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


    @RequestMapping("/filesUpload")
    @ResponseBody
    //requestParam要写才知道是前台的那个数组
    public String filesUpload(@RequestParam(name = "files") MultipartFile[] files,
                              @RequestParam(name = "filess") MultipartFile filess,
                              @RequestParam(name = "myfiles") MultipartFile[] myfiles,
                              InsertScenry insertScenry, HttpServletRequest request) {
        InsertScenry is = insertScenry;
        Mark mark = (Mark) request.getSession().getAttribute("mark");
        System.out.println("商家id" + mark.getM_id());
        System.out.println("标题" + insertScenry.getS_title());
        System.out.println("详情" + insertScenry.getS_txt());
        System.out.println("价格" + insertScenry.getS_price());
        System.out.println("原价" + insertScenry.getS_price_yuan());
        List<String> list = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        int s = (int) (Math.random() * 100000) + 1;
        String mz = "beiji_" + df.format(new Date()) + s;
        String dz = "C:/tomcat_media/webapps/walk_img/" + mz;
        String fm = "C:/tomcat_media/webapps/walk_fmImg";
        is.setM_id(mark.getM_id());
        is.setS_img(dz);
        is.setS_fmImg("walk_fmImg/" + filess.getOriginalFilename());
        boolean orw = sve.insertScenry(is);
        if (orw == false) {
            return "shib";
        } else {
            int isf = 0;//用来判断上传到哪个路径
            if (filess != null) {
                isf = 2;
                list = saveFile(request, filess, list, s, isf, fm);
            }
            if (files.length < 6 && files.length > 0) {
                if (myfiles.length > 0) {
                    if (files != null && files.length > 0) {
                        isf = 0;
                        for (int i = 0; i < files.length; i++) {
                            MultipartFile file = files[i];
                            System.out.println("wjm" + file);
                            // 保存文件
                            list = saveFile(request, file, list, s, isf, dz);
                        }
                    }
                    //写着测试，删了就可以
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("集合里面的数据" + list.get(i));
                    }
                } else {
                    return "nomin";
                }
            } else {
                return "nomax";
            }
            if (myfiles.length > 0) {
                if (files != null && files.length > 0) {
                    if (myfiles != null && myfiles.length > 0) {
                        isf = 1;
                        for (int i = 0; i < myfiles.length; i++) {
                            MultipartFile myfile = myfiles[i];
                            list = saveFile(request, myfile, list, s, isf, dz);
                        }
                    }
                } else {
                    return "nomax";
                }
            } else {
                return "nomin";
            }

            return "somss";
        }
    }

    private List<String> saveFile(HttpServletRequest request,
                                  MultipartFile file, List<String> list, int s, int isf, String path) {
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
                String filePath = "";
                if (isf == 0) {
                    filePath = path + "/lb/" + min;
                } else if (isf == 1) {
                    filePath = path + "/xq/" + min;
                } else if (isf == 2) {
                    filePath = path + "/" + min;
                }
                System.out.println("地址" + filePath);
                list.add(file.getOriginalFilename());
                System.out.println("图片" + file.getOriginalFilename());
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

    @RequestMapping("/dataDel.action")
    @ResponseBody
    public Message dataDelUser(String id) throws IOException {
        System.out.println(id);
        String[] str = id.split(",");
        Integer[] is = new Integer[str.length];
        for (int i = 0; i < str.length; i++) {
            is[i] = Integer.parseInt(str[i]);
            System.out.println("删除id" + is[i]);
        }
        List<Integer> list = Arrays.asList(is);

        Message m = new Message();
        if (this.sve.deleteScenry(list) > 0) {
            m.setFlag(true);
            m.setMessage("删除成功！");
        } else {
            m.setFlag(false);
            m.setMessage("删除失败！");
        }
        return m;
    }

    @RequestMapping("/selectEcharts")
    @ResponseBody
    public List<Echarts> selectEcharts(HttpServletRequest request){
        Mark mark = (Mark) request.getSession().getAttribute("mark");
        int m_id = mark.getM_id();
        System.out.println("爱好就是长三角"+m_id);
        List<Echarts> list = this.mve.selectEcharts(m_id);
        for (Echarts e:list) {
            System.out.println("把市场部"+e.getS_title());
        }
        return  list;
    }

}
