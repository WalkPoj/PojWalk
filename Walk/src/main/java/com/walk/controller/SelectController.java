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

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class SelectController {

    @Autowired
    public SelectDaoService sdaos;

    @Autowired
    private RedisTemplate redisTemplate;


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

    /**
     * 查看更多
     * @param mod
     * @param sPage
     * @param ePage
     * @return
     */
    @RequestMapping("listView")
    public String ListView(Model mod,int sPage,int ePage){
        PageInfo<Scenery> pageInfo = sdaos.listView(mod,sPage,ePage);
        //总页数
        mod.addAttribute("pageNum",pageInfo.getPages());
        //当前页数
        mod.addAttribute("pageNext",pageInfo.getPageNum());
        //单页数据
        mod.addAttribute("sList",pageInfo.getList());
        System.out.println("第"+sPage+"页数据将在60后被销毁"+redisTemplate.expire("lvScenery::data_page_"+sPage,60,TimeUnit.SECONDS));
        return "cpts_398_pn/products";
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
        //测试放入一个用户
        session.setAttribute("user_id","11");
        String u_id = (String)session.getAttribute("user_id");
        //存班次
        redisTemplate.opsForValue().set("Data_INFO_"+u_id,dci);
        return "点击现在支付进行，进行下一步吧~";
    }

    /**
     * 加载订单,提交至订单页面
     * @return
     */
    @RequestMapping("listOrder")
    public String OrderHtml(HttpSession session,Model mod,Data_Order data_order){
        System.out.println(data_order.getPerson_num());
        String u_id = (String)session.getAttribute("user_id");
        mod.addAttribute("Data_order",data_order);
        //添加班次id
        session.setAttribute("Data_INFO",(Data_Class_INFO)redisTemplate.opsForValue().get("Data_INFO_"+u_id));
        return "order/index";
    }

    /**
     * 保存订单
     * @return
     */
    @PostMapping("SaveOrder")
    public String SaveOrder(HttpSession session,Order_info oi){
        //生成订单号
        String Order_id = FileUtil.getOrderIdByUUId();
        //获取用户id
        String u_id = (String) session.getAttribute("user_id");
        //获取车票信息
        Data_Class_INFO dci=(Data_Class_INFO) session.getAttribute("Data_INFO");
        //新增订单实体类
        The_order to = new The_order();
        to.setO_id(Order_id);
        to.setU_id(Integer.valueOf(u_id));
        to.setU_phone(oi.getU_phone());
        to.setM_id(oi.getM_id());
        to.setS_id(oi.getS_id());
        to.setO_person(oi.getU_lv_name().size());
        to.setTools_id(dci.getTools());
        to.setClass_id(dci.getData_id());
        to.setO_price(oi.getOrder_price());
        //保存订单
        sdaos.SaveOrder(to);
        //新增旅客实体类
        for (int i = 0;i < oi.getU_lv_name().size() ; i++) {
            Person per = new Person();
            per.setU_id(Integer.valueOf(u_id));
            per.setPe_name(oi.getU_lv_name().get(i));
            per.setPe_cardid(oi.getU_lv_cardid().get(i));
            per.setPe_phone(oi.getU_lv_phone().get(i));
            sdaos.Saveperson(per);
        }
        return "index";
    }
    public static void main(String[] args) {
        System.out.println(FileUtil.getOrderIdByUUId());
    }
}
