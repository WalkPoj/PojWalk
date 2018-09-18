package com.walk.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.walk.pojo.*;
import com.walk.service.SelectDaoService;
import com.walk.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Service;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
public class SelectController {

    @Autowired
    public SelectDaoService sdaos;

    @Autowired
    private RedisTemplate redisTemplate;

    private int rowA = 0;

    private Map<String,Integer> bing = new HashMap<>();
    private int s_city = 0;
    private int s_price = 0 ;
    private int s_num = 0;

    /**
     * 查询首页推荐
     * @param mod
     * @return
     */
    @RequestMapping("indexShow")
    public String Showimg(Model mod){
        List<Scenery> s = sdaos.indexSelect();
        redisTemplate.expire("indexScenery::data_index",120,TimeUnit.SECONDS);
        mod.addAttribute("scenery",s);
        return "cpts_398_pn/deals";
    }

    @RequestMapping("listView.html")
    public String lvhtml(){
        return "listView";
    }
    /**
     * 查看更多
     * @param mod
     * @param sPage
     * @param ePage
     * @return
     */
    @RequestMapping("listView")
    public String ListView(Model mod,int sPage,int ePage,HttpSession session){
        if (rowA == 0){
            PageInfo<Scenery> pageInfo = sdaos.listView(mod,sPage,ePage);
            //总页数
            mod.addAttribute("pageNum",pageInfo.getPages());
            //当前页数
            mod.addAttribute("pageNext",pageInfo.getPageNum());
            //单页数据
            mod.addAttribute("sList",pageInfo.getList());
            System.out.println("第"+sPage+"页数据将在60后被销毁"+redisTemplate.expire("lvScenery::data_page_"+sPage,60,TimeUnit.SECONDS));
        }else{
            PageInfo<Scenery> pageInfo = new PageInfo<>((List)session.getAttribute("EndSee"));
            //总页数
            mod.addAttribute("pageNum",pageInfo.getPages());
            //当前页数
            mod.addAttribute("pageNext",pageInfo.getPageNum());
            //单页数据
            mod.addAttribute("sList",pageInfo.getList());

        }
        return "cpts_398_pn/products";

    }

    @RequestMapping("Shaixuan")
    public String Shaixuan(String Da, String Dc,HttpSession session){
        bing.put("评分",0);
        bing.put("地区",0);
        bing.put("价格区间",0);

        String[] name = Da.split(":");
        if (name[0].equals("评分")){
            s_num = 1;
            bing.put(name[0],s_num);
        }else if (name[0].equals("地区")){
            if (Dc.equals("张家界")){
                s_city = 148;
            }else if (Dc.equals("三亚")){
                s_city = 101;
            }else if (Dc.equals("厦门")){
                s_city = 123;
            }else if(Dc.equals("北京")){
                s_city = 5;
            }else if (Dc.equals("西安")){
                s_city = 124;
            }else if (Dc.equals("青岛")){
                s_city = 93;
            }else if (Dc.equals("贵州")){
                s_city = 34;
            }else if (Dc.equals("西藏")){
                s_city = 125;
            }
            bing.put(name[0],s_city);
        }else if (name[0].equals("价格区间")){
            if (Dc.equals("0-1000")){
                s_price = 999;
            }else if (Dc.equals("1000-2500")){
                s_price = 2499;
            }else if (Dc.equals("2500-3500")){
                s_price = 3499;
            }else if (Dc.equals("5500-9500")){
                s_price = 8499;
            }else {
                s_price = 9599;
            }
            bing.put(name[0],s_price);
        }
        session.setAttribute("EndSee",sdaos.selectByMod(bing.get("地区"),bing.get("价格区间"),bing.get("评分")));
        rowA = 1;
        return "forward:listView?sPage=1&ePage=11";
    }

    /**
     * 取消条件
     * @return
     */
    @PostMapping("Quxiao")
    @ResponseBody
    public String cacanleData(String Da) {
        String[] name = Da.split(":");
        bing.put(name[0],0);
        System.out.println(bing.size());
        //Shai = sdaos.selectByMod(bing.get("地区"),bing.get("价格区间"),bing.get("评分"));
        return "";
    }

    /**
     * 加载详情页面
     * @return
     */
    @RequestMapping("details.html")
    public String details(int s_id,Model mod){
        mod.addAttribute("s_id",s_id);
        return "details";
    }
    @RequestMapping("listDetails")
    public String listDetails(int s_id,Model mod){
       Scenery sce =  sdaos.selectDeal(s_id);

       String imgPath = sce.getS_img();
       List imglb = FileUtil.getFiles(imgPath+"/lb");
       List imgXq = FileUtil.getFiles(imgPath+"/xq");
       String[] dir = imgPath.split("\\\\");
       mod.addAttribute("imglb",imglb);
       mod.addAttribute("imgXq",imgXq);
       mod.addAttribute("s_Details",sce);
       mod.addAttribute("Dir",dir[4]);
       mod.addAttribute("c_name",sdaos.selectCname(sce.getS_city()));
       mod.addAttribute("class_num","aaa");
       return "cpts_398_pn/p-single";
    }

    /**
     * ajaxa查询车票
     * @return
     */
    @PostMapping("Select_Bin")
    @ResponseBody
    public int selectBin(DataPiao dp){
        int i = sdaos.selectSeid(dp);
        System.out.println("正在查询线路id,线路id为："+i);
        return i;
    }
    @PostMapping("Select_Car")
    @ResponseBody
    public List<Carclass> Select_Car(DataPiao dp){
        List<Carclass> cl = sdaos.selectCar(dp);
        System.out.println("查询汽车票线路id为"+dp.getSe_id()+"出发时间为"+dp.getStime()+"是否查到"+cl.size());
        return cl;
    }
    @PostMapping("Select_Train")
    @ResponseBody
    public List<Trainclass> Select_Train(DataPiao dp){
        List<Trainclass> tl = sdaos.selectTrain(dp);
        System.out.println("查询火车票线路id为"+dp.getSe_id()+"出发时间为"+dp.getStime()+"是否查到"+tl.size());
        return tl;
    }
    @PostMapping("Select_Plane")
    @ResponseBody
    public List<Planeclass> Select_Plane(DataPiao dp){
        List<Planeclass> pl = sdaos.selectPlane(dp);
        System.out.println("查询飞机票线路id为"+dp.getSe_id()+"出发时间为"+dp.getStime()+"是否查到"+pl.size());
        return pl;
    }

    /**
     * 保存车票信息
     * @return
     */
    @PostMapping("Save_Redis")
    @ResponseBody
    public String Save_Redis(HttpSession session, Data_Class_INFO dci){
        User u =(User)session.getAttribute("user");
        if (u != null){
            //存班次
            redisTemplate.opsForValue().set("Data_INFO_"+u.getU_id(),dci);
        }else{
            return "0";
        }
        return "现在去支付吧~";
    }

    /**
     * 加载订单,提交至订单页面
     * @return
     */
    @RequestMapping("listOrder")
    public String OrderHtml(HttpSession session,Model mod,Data_Order data_order){
        User u = (User)session.getAttribute("user");
        if (u != null){
            Data_Class_INFO dci = (Data_Class_INFO)redisTemplate.opsForValue().get("Data_INFO_"+u.getU_id());
            if (dci == null){
                System.out.println("请选择班次号");
                mod.addAttribute("class_num","请选择班次号");
                return "cpts_398_pn/p-single";
            }else{
                mod.addAttribute("Data_order",data_order);
                //添加班次id
                session.setAttribute("Data_INFO",(Data_Class_INFO)redisTemplate.opsForValue().get("Data_INFO_"+u.getU_id()));
                return "order/index";
            }
        }
        return "forward:indexLogin.action";
    }

    /**
     * 保存订单
     * @return
     */
    @RequestMapping("SaveOrder")
    public String SaveOrder(int uid,HttpSession session){
        //获取订单
        Order_info oi =(Order_info)redisTemplate.opsForValue().get("pay_oi_"+uid);
        //获取车票信息
        Data_Class_INFO dci =(Data_Class_INFO)redisTemplate.opsForValue().get("pay_dci_"+uid);
        //获取用户id
        User u = (User)redisTemplate.opsForValue().get("pay_user_"+uid);
        session.setAttribute("user",u);
        //生成订单号
        String Order_id = FileUtil.getOrderIdByUUId();
        System.out.println(u.getU_id());
        //新增订单实体类
        The_order to = new The_order();
        to.setO_id(Order_id);
        to.setU_id(u.getU_id());
        to.setU_phone(oi.getU_phone());
        to.setM_id(oi.getM_id());
        to.setS_id(oi.getS_id());
        to.setO_person(oi.getU_lv_name().size());
        to.setTools_id(dci.getTools());
        to.setClass_id(dci.getData_id());
        to.setO_price(oi.getOrder_price());
        to.setO_create(new Date());
        //保存订单
        sdaos.SaveOrder(to);
        //新增旅客实体类
        for (int i = 0;i < oi.getU_lv_name().size() ; i++) {
            Person per = new Person();
            per.setU_id(u.getU_id());
            per.setPe_name(oi.getU_lv_name().get(i));
            per.setPe_cardid(oi.getU_lv_cardid().get(i));
            per.setPe_phone(oi.getU_lv_phone().get(i));
            sdaos.Saveperson(per);
        }
        return "redirect:index.html";
    }

}
