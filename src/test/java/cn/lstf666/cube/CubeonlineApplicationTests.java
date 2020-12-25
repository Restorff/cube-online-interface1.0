package cn.lstf666.cube;

import cn.lstf666.cube.controller.adminController;
import cn.lstf666.cube.model.Admin;
import cn.lstf666.cube.model.Competition;
import cn.lstf666.cube.model.Player;
import cn.lstf666.cube.model.Result5;
import cn.lstf666.cube.service.AdminService;
import cn.lstf666.cube.utils.MailUtils;
import cn.lstf666.cube.utils.UuidUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.*;

@SpringBootTest
class CubeonlineApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    private AdminService adminService ;



    @Test
    void contextLoads() {

    }

    /**
     * @Author liaotao
     * @Date 11:18 2020/10/3
     * @Description 测试数据源是否切换为Druid
     * @return void
     **/
    @Test
    void testDataSources() {
        System.out.println(dataSource.getClass());
    }


    @Test
    /**
     * 功能描述: 查看所有比赛测试类
     *
     * @Param: []
     * @return: void
     * @Author: Restorff
     * @Date: 2020/10/4 16:58
    */
    void checkGame(){
        Map<String,Object> res = new HashMap<String, Object>();
        List<Competition> competitions = adminService.checkGame();
        if (competitions != null){
            res.put("msg", "比赛查询成功");
            res.put("data",competitions);
        }else {
            res.put("msg", "暂无进行的比赛");
            res.put("data",competitions);
        }
        System.out.println(res);
    }

//    @Test
//    void adminRegister() {
//        Map<String, String> res = new HashMap<String, String>();
//
//        Admin admin = new Admin("lstf","123","1350216878","1350216878@qq.com","13032818128","男");
//        //1. 用户名查重
//        if (adminService.queryAdminByUsername(admin.getUsername()) == true) {
//            //用户名已存在
//            res.put("type", "error");
//            res.put("msg", "用户名已存在");
//        }
//        //2. 邮箱查重
//        if (adminService.queryAdminByEmail(admin.getEmail()) == true) {
//            //邮箱已经注册
//            res.put("type", "error");
//            res.put("msg", "邮箱已被注册");
//        }
//        //3. 设置code
//        String code = UuidUtil.getUuid();
//        admin.setCode(code);
//
//        //4. 发送邮件
//        MailUtils.sendMail(admin.getEmail(), "<a href='http://127.0.0.1:8080/admin/active?code=" +
//                code + "'>点击激活你的账户</a>", "CubeOnline激活邮件");
//        //5. 信息持久化数据库
//
//        if (adminService.register(admin) == 1) {
//            res.put("type", "success");
//            res.put("msg", "注册成功");
//        } else {
//            res.put("type", "error");
//            res.put("msg", "注册失败");
//        }
//
//        System.out.println(res);
//    }

    
    /**
     * 功能描述: 
     *
     * @Param: 登录测试类
     * @return: 
     * @Author: Restorff
     * @Date: 2020/10/4 17:31
    */
    @Test
    void login(){
        Map<String, String> res = new HashMap<String, String>();
        String username = "root";String password  = "123456";

        MockHttpServletRequest request = new MockHttpServletRequest();
        if (username==null || password==null || "".equals(username) || "".equals(password)){
            //用户名或者密码为空
            res.put("type","error");
            res.put("msg","用户名或者密码为空,请检查后输入");
        }

        Admin loginAdmin = adminService.login(username, password);
        if(loginAdmin == null){
            res.put("type","error");
            res.put("msg","用户名或者密码错误，请检查后输入");
        }

        request.getSession().setAttribute("admin",loginAdmin.getUsername());
        if (loginAdmin.getUsername().equals("root")){
            //超级管理员
            res.put("msg","超级管理员登录成功");
            res.put("isRoot","1");
        }else {
            res.put("type", "success");
            res.put("msg", "管理员登录成功");
        }
        res.put("type","success");

        System.out.println(res);
        System.out.println(request.getSession().getAttribute("admin"));
    }


    /**
     * 功能描述: 得到一场比赛测试类
     *
     * @Param:
     * @return:
     * @Author: Restorff
     * @Date: 2020/10/4 17:36
    */
    @Test
    void manage(){
        Integer cId = 1;
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

        System.out.println(res);
    }



    /**
     * 功能描述:
     *
     * @Param: 添加成绩测试
     * @return:
     * @Author: Restorff
     * @Date: 2020/10/6 16:55
    */
    @Test
    void addOneResult(){
        Result5 result5 = new Result5(1,4,"李四","e333",1,12,11,10.2,13.2,15.1);
        Map<String, Object> res = new HashMap<String, Object>();
        int i = adminService.addAnResult5(result5);
        System.out.println(i);
    }

    /**
     * 功能描述:修改成绩测试
     *
     * @Param: 添加成绩测试
     * @return:
     * @Author: Restorff
     * @Date: 2020/10/6 16:55
     */
    @Test
    void changeOneResult(){
        Result5 result5 = new Result5(1,4,"李四","e333",2,1,1,1.2,1.2,1.1,11.22,12.22);

        int i = adminService.changeAnResult5(result5);
        System.out.println(i);
    }


    /**
     * 功能描述:删除成绩测试
     *
     * @Param:
     * @return:
     * @Author: Restorff
     * @Date: 2020/10/6 16:55
     */
    @Test
    void deleteOneResult(){
        Result5 result5 = new Result5(1,4,"李四","e333",1,1,1,1.2,1.2,1.1,1,1.22);

        int i = adminService.deleteAnResult5(result5.getrId());
        System.out.println(i);
    }



    /**
     * 功能描述: 拿到当前比赛的所有人的id列表
     *
     * @Param:
     * @return:
     * @Author: Restorff
     * @Date: 2020/10/6 22:07
    */
    @Test
    void getAllPlayers(){
        List<Integer> ids = adminService.getIds(1);
        List<Integer> listNew = new ArrayList<Integer>(new LinkedHashSet(ids));
        for( int id : listNew){
            System.out.println(id);
        }
    }


    /**
     * 功能描述: 根据id拿到一个人的所有信息
     *
     * @Param:
     * @return:
     * @Author: Restorff
     * @Date: 2020/10/6 22:08
    */
    @Test
    void getOnePlayer(){
        Player onePlayer = adminService.getOnePlayer(1);
        System.out.println(onePlayer);
    }


//    @Test
//    void getResultList(){
//        Map<String, Object> res = new HashMap<String, Object>();
//
//        List<Object> playList = new ArrayList<>();
//        List<Integer> idList = new ArrayList<>();
//        List<Result5> result5List = new ArrayList<>();
//        Map<String, Object> playerInfo  ;
//
//        Player player = new Player();
//        //1 查询共有多少个人参加当前比赛,返回一个list
//        idList = adminService.getIds(1);
//        //2 遍历list的所有id
//        for (int pId : idList){
//            //2.1 查询每个id的信息，返回player对象
//            player = adminService.getOnePlayer(pId);
//            //2.2 查询result5，返回每个id的成绩信息，返回List<result5>
//            result5List = adminService.getResult5List(pId);
//
//            playerInfo = new HashMap<>();
//            //2.3 将数据put进playerInfo
//            playerInfo.put("player",player);
//            playerInfo.put("result5",result5List);
//
//            //2.4 将playerInfo加进一个list
//            playList.add(playerInfo);
//        }
//
//        //3 将list放进res的data
//        res.put("type","success");
//        res.put("msg","查询成功");
//        res.put("data",playList);
//
//        System.out.println(res);
//
//    }
}
