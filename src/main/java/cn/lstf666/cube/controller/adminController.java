package cn.lstf666.cube.controller;


import cn.lstf666.cube.model.*;
import cn.lstf666.cube.service.AdminService;
import cn.lstf666.cube.utils.MailUtils;
import cn.lstf666.cube.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: cube-online
 * @description: 普通管理员控制器
 * @author: Restorff
 * @create: 2020-10-04 10:49
 **/
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private AdminService adminService ;

    /**
     * 功能描述: 管理员注册
     *
     * @Param: [admin]
     * @return: java.util.Map<java.lang.String,java.lang.String>
     * @Author: Restorff
     * @Date: 2020/10/4 10:20
    */
    @RequestMapping("/register")
    public Map<String,String> register( Admin admin) {
        System.out.println(admin);
        Map<String,String> res = new HashMap<String, String>();

        if (admin.getUsername()==null || admin.getPassword()==null || "".equals(admin.getUsername()) || "".equals(admin.getPassword())){
            //用户名或者密码为空
            res.put("type","error");
            res.put("msg","用户名或者密码为空,请检查后输入");
            return res;
        }

        //1. 用户名查重
        if (adminService.queryAdminByUsername(admin.getUsername()) == true ) {
            //用户名已存在
            res.put("type","error");
            res.put("msg","用户名已存在");
            return res;
        }
        //2. 邮箱查重
        if (adminService.queryAdminByEmail(admin.getEmail()) == true) {
            //邮箱已经注册
            res.put("type","error");
            res.put("msg","邮箱已被注册");
            return res;
        }
        //3. 设置code
        String code = UuidUtil.getUuid();
        admin.setCode(code);

        //4. 发送邮件
//        ---------------------------------------------------------------
        MailUtils.sendMail("1350216878@qq.com","<a href='http://cube-online.lstf666.cn:8083/Cube-Online/root/activeAdmin?code="+
                code+"'>点击激活这个管理员的账户</a>","CubeOnline激活邮件");
//        ---------------------------------------------------------------

        //5. 信息持久化数据库
        int rcode = adminService.register(admin);
        if (rcode == 1){
            res.put("type","success");
            res.put("msg","注册成功");
        }else {
            res.put("type","error");
            res.put("msg","注册失败");
        }
        return res;
    }



    @RequestMapping("/login")
    /**
     * 功能描述: 管理员登录
     *
     * @Param: [username, password, request]
     * @return: java.util.Map<java.lang.String,java.lang.String>
     * @Author: Restorff
     * @Date: 2020/10/4 11:24
    */
    public Map<String,Object> login(String username, String password, HttpServletRequest request) {
        Map<String,Object> res = new HashMap<String, Object>();
         if (username==null || password==null || "".equals(username) || "".equals(password)){
             //用户名或者密码为空
             res.put("type","error");
             res.put("msg","用户名或者密码为空,请检查后输入");
             return res;
         }

        Admin loginAdmin = adminService.login(username, password);
         if(loginAdmin == null){
             res.put("type","error");
             res.put("msg","用户名或者密码错误，请检查后输入");
             return res;
         }

        request.getSession().setAttribute("admin",loginAdmin.getUsername());
         if (loginAdmin.getUsername().equals("root")){
             //超级管理员
             res.put("msg","超级管理员登录成功");
             res.put("isRoot","1");
             res.put("admin",loginAdmin);
         }else {
             res.put("type", "success");
             res.put("msg", "管理员登录成功");
             res.put("admin",loginAdmin);
         }
//         request.getSession().setAttribute("admin",);
        res.put("type","success");
        return res;
    }


//    @RequestMapping("/logout")
    /**
     * 功能描述: 管理员登出
     *
     * @Param:  request
     * @return: java.util.Map<java.lang.String,java.lang.String>
     * @Author: Restorff
     * @Date: 2020/10/4 11:24
     */
//    public Map<String,String> logout(HttpServletRequest request) {
//        Map<String,String> res = new HashMap<String, String>();
//
//
//        if (request.getSession().getAttribute("admin")!=null){
//            request.getSession().removeAttribute("admin");
//            res.put("type", "success");
//            res.put("msg","管理员登出成功");
//        }else {
//            res.put("type", "error");
//            res.put("msg", "当前尚未登陆");
//        }
//        return res;
//
//    }


    @RequestMapping("/checkGame")
    /**
     * 功能描述:查看所有当前进行的比赛
     *
     * @Param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: Restorff
     * @Date: 2020/10/4 12:34
    */
    public Map<String,Object> checkGame() {
        Map<String,Object> res = new HashMap<String, Object>();
        List<Competition> competitions = adminService.checkGame();
        if (competitions != null){
            res.put("type", "success");
            res.put("msg", "比赛查询成功");
            res.put("data",competitions);
        }else {
            res.put("type", "error");
            res.put("msg", "暂无进行的比赛");
            res.put("data",competitions);
        }
        return res;
    }


    @RequestMapping("/manage")
    /**
     * 功能描述: 查看并管理一场比赛
     *
     * @Param: 比赛id
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: Restorff
     * @Date: 2020/10/4 13:29
     */
    public Map<String,Object> manage(Integer cId) {
        Map<String, Object> res = new HashMap<String, Object>();
        Competition competition = adminService.getOneGame(cId);
        if (competition != null){
            res.put("type", "success");
            res.put("msg", "比赛信息查询成功");
            res.put("data",competition);
        }else {
            res.put("type", "error");
            res.put("msg", "该比赛当前不存在，请重新检查联系站长解决");
            res.put("data",competition);
        }
        return res;
    }



    @RequestMapping("/add")
    /**
     * 功能描述: 添加一条成绩
     *
     * @Param: 一条成绩对象
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: Restorff
     * @Date: 2020/10/4 15:19
    */
    public Map<String,Object> add( Result5 result5) {
        Map<String, Object> res = new HashMap<String, Object>();
//        System.out.println("id-------"+result5.getpId());
//        System.out.println("name-------"+result5.getName());

        int i = adminService.addAnResult5(result5);
        if(i==1){
            res.put("type", "success");
            res.put("msg", "记录成功");
        }else if(i== -1){
            res.put("type", "error");
            res.put("msg","该选手未报名本项目！请检查后输入！");
        }else {
            res.put("type", "error");
            res.put("msg","您已输入过该人本轮次的成绩，请勿重反复输入！！");
        }
        return res;
    }


    @RequestMapping("/change")
    /**
     * 功能描述: 修改一条成绩对象
     *
     * @Param: 一条成绩对象
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: Restorff
     * @Date: 2020/10/4 16:20
    */
    public Map<String,Object> change( Result5 result5) {
        Map<String, Object> res = new HashMap<String, Object>();
        int i = adminService.changeAnResult5(result5);
        if(i==1){
            res.put("type", "success");
            res.put("msg", "修改成功");
        }else {
            res.put("type", "error");
            res.put("msg","修改失败！！");
        }
        return res;
    }



    @RequestMapping("/delete")
    /**
     * 功能描述: 删除一条成绩
     *
     * @Param: 一条成绩id
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: Restorff
     * @Date: 2020/10/4 22:27
    */
    public Map<String,Object> delete(int rId) {
        Map<String, Object> res = new HashMap<String, Object>();
        int i = adminService.deleteAnResult5(rId);
        if(i==1){
            res.put("type", "success");
            res.put("msg", "删除成功");
        }else {
            res.put("type", "error");
            res.put("msg","删除失败！！");
        }
        return res;
    }



    @RequestMapping("/getPlayers")
   /**
    * 功能描述: 查看一场比赛所有选手的比赛信息
    *
    * @Param: [cid]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Restorff
    * @Date: 2020/10/4 16:31
   */
    public Map<String,Object> getPlayers(int cId) {
        Map<String, Object> res = new HashMap<String, Object>();

        List<Object> playList = new ArrayList<Object>();
        List<Integer> idList  ;
        List<Result5> result5List  ;
        Apply applyList;
        Map<String, Object> playerInfo  ;

        Player player ;
        //1 查询共有多少个人参加当前比赛,返回一个list
        idList = adminService.getIds(cId);
        System.out.println("当前比赛Id"+cId+",");
        System.out.println("当前选手id：");
        for(int i : idList){
            System.out.println("--"+i);
        }
        //2 遍历list的所有id
        for (int pId : idList){
            //2.1 查询每个id的信息，返回player对象
            player = adminService.getOnePlayer(pId);
            //2.2 查询result5，返回每个id的成绩信息，返回List<result5>
            result5List = adminService.getResult5List(cId,pId);
            System.out.println("result5List"+result5List);
            applyList = adminService.getApplyList(cId,pId);
            System.out.println("applyList:"+applyList);
            playerInfo = new HashMap<String, Object>();
            //2.3 将数据put进playerInfo
            playerInfo.put("player",player);
            playerInfo.put("result5",result5List);
            playerInfo.put("apply" ,applyList);

            //2.4 将playerInfo加进一个list
            playList.add(playerInfo);
        }

        //3 将list放进res的data
        res.put("type","success");
        res.put("msg","查询成功");
        res.put("data",playList);

        return res;
    }




    @RequestMapping("/getScores")
    /**
     * 功能描述: 查询所有成绩对象
     *
     * @Param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: Restorff
     * @Date: 2020/10/4 18:22
     */
    public Map<String,Object> getScores(int cId,String event) {
        Map<String, Object> res = new HashMap<String, Object>();
        List scores = new ArrayList();
        int rounds = adminService.getRounds(cId, event);
//        System.out.println("cId-----"+cId);
//        System.out.println("event-----"+event);
//        System.out.println("rounds-----"+rounds);
        for(int i=1;i<=rounds;i++){
            List score_aRound = adminService.getScores(cId, event,i);
            scores.add(score_aRound);
        }
        if (scores == null){
            res.put("type","error");
            res.put("msg","当前项目未开放");
        }else {
            res.put("type","success");
            res.put("msg","查询成功");
            res.put("scoreList",scores);
        }

        return res;
    }


    @RequestMapping("/getRounds")
    /**
     * 功能描述: 
     *
     * @Param: 得到当前项目的轮次
     * @return: 
     * @Author: Restorff
     * @Date: 2020/10/10 17:10
    */
    public Map<String,Object> getRounds(int cId,String event) {
        Map<String, Object> res = new HashMap<String, Object>();
        int rounds = adminService.getRounds(cId, event);
            res.put("type","success");
            res.put("msg","查询成功");
            res.put("rounds",rounds);

        return res;
    }


    /**
     * 功能描述:
     *
     * @Param: 得到一条成绩对象以供修改时使用
     * @return:
     * @Author: Restorff
     * @Date: 2020/10/12 17:27
    */
    @RequestMapping("/getOneScore")
    public Map<String,Object> getOneScore(int rId) {
//        System.out.println("----rId=="+rId);
        Map<String, Object> res = new HashMap<String, Object>();
        Result5 result5 = adminService.getOneScore(rId);
        res.put("type","success");
        res.put("msg","查询成功");
        res.put("result_5",result5);

        return res;
    }
}
