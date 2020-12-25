package cn.lstf666.cube.controller;

import cn.lstf666.cube.model.Apply;
import cn.lstf666.cube.model.Competition;
import cn.lstf666.cube.model.Player;
import cn.lstf666.cube.model.Result5;
import cn.lstf666.cube.service.AdminService;
import cn.lstf666.cube.service.PlayService;
import cn.lstf666.cube.utils.MailUtils;
import cn.lstf666.cube.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @Author liaotao
 * @Date 2020/10/3 22:04
 */
@SuppressWarnings("all")
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/player")
public class PlayController {

    @Autowired
    private PlayService playService;

    @Autowired
    private AdminService adminService;


    /**
     * @Author liaotao
     * @Date 22:42 2020/10/3
     * @Description 选手注册
     * @Param [player]
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    @RequestMapping("/register")
    public Map<String,String> register( Player player) {
        Map<String,String> res = new HashMap<String,String>();
        //用户名查重
        if (playService.queryPlayerByUsername(player.getUsername()) != null) {
            //用户名已存在
            res.put("type","error");
            res.put("msg","用户名已存在");
            return res;
        }
        //邮箱查重
        if (playService.queryPlayerByEmail(player.getEmail()) != null) {
            //邮箱已经注册
            res.put("type","error");
            res.put("msg","邮箱已被注册");
            return res;
        }
        //获取uuid
        String code = UuidUtil.getUuid();
        player.setCode(code);
        //发送邮件
        MailUtils.sendMail(player.getEmail(),"<a href='http://cube-online.lstf666.cn:8083/Cube-Online/player/active?code="+code+"'>点击激活你的账户</a>","CubeOnline激活邮件");
        //信息持久化数据库
        playService.register(player);
        res.put("type","success");
        res.put("msg","注册成功");
        return res;
    }

    /**
     * @Author liaotao
     * @Date 22:52 2020/10/3
     * @Description 选手激活
     * @Param [code]
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    @RequestMapping("/active")
    public void active(String code, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        //1.根据code查询数据库中选手信息
        Player player = playService.queryPlayerByCode(code);
        PrintWriter out;
        if (player == null) {
            try {
                out = response.getWriter();
                out.print("<script>alert('所激活用户不存在!');</script>");
                out.close();
                response.getWriter().println("error,所激活用户不存在!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //2.将该选手激活状态设置为y
        int result = playService.active(player.getpId());
        if (result == 1) {
            try {
                out = response.getWriter();
                out.print("<script>alert('激活成功!');</script>");
                out.close();
                response.getWriter().println("error,激活成功!");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            try {
                out = response.getWriter();
                out.print("<script>alert('激活失败，请联系站长!!');</script>");
                out.close();
                response.getWriter().println("error,激活失败，请联系站长!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Author liaotao
     * @Date 12:54 2020/10/4
     * @Description 选手登录
     * @Param [username, password]
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    @RequestMapping("/login")
    public Map<String,Object> login (String username,String password) {
        Map<String,Object> res = new HashMap<String,Object>();
        // 查询用户名是否存在
        Player player = playService.queryPlayerByUsername(username);
        if (player == null) {
            res.put("type","error");
            res.put("msg","用户名不存在");
            return res;
        }
        // 密码是否正确
        if (!password.equals(player.getPassword())) {
            res.put("type","error");
            res.put("msg","用户名或密码错误");
            return res;
        }
        // 用户是否激活
        if (!"y".equals(player.getStatus())) {
            res.put("type","error");
            res.put("msg","尚未激活账号");
            return res;
        }
        // 登录成功
        res.put("type","success");
        res.put("msg","登录成功");
        res.put("player",player);
        return res;
    }

    /**
     * @Author liaotao
     * @Date 15:23 2020/10/5
     * @Description 查询报名信息
     * @Param [pid]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/queryGame")
    public Map<String,Object> queryGame(int pid) {
        Map<String,Object> res = new HashMap<String,Object>();
        //存放已经参加比赛
        List<Competition> data1 = new ArrayList<Competition>();
        //存放未参加的比赛
        List<Competition> data2 = new ArrayList<Competition>();
        //所有比赛
        List<Competition> competitions = adminService.checkGame();
        // 根据pid 查询 cid[]
        List<Integer> ids = playService.querycIdBypId(pid);
        // 根据id查询参加比赛对象
        for (Competition competition : competitions) {
            if (ids.contains(competition.getcId())) {
                data1.add(competition);
            } else {
                data2.add(competition);
            }
        }
        res.put("type","success");
        res.put("msg","查询成功");
        res.put("data1",data1);
        res.put("data2",data2);
        return res;
    }


    /**
     * @Author liaotao
     * @Date 16:08 2020/10/5
     * @Description 选手报名比赛
     * @Param [apply]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/chooseEvent")
    public Map<String,Object> chooseEvent( Apply apply) {
        Map<String,Object> res = new HashMap<String,Object>();
        //查看报名信息是否满
        Competition oneGame = adminService.getOneGame(apply.getcId());
        if (oneGame.getNowNum() == oneGame.getMaxNum()) {
            //人数已满
            res.put("type","error");
            res.put("msg","比赛人数已满，请联系主办方");
            return res;
        }

        //保存报名信息
        int result = playService.chooseEvent(apply);
        if (result != 1) {
            res.put("type","error");
            res.put("msg","报名失败，请联系主办方");
            return res;
        }
        res.put("type","success");
        res.put("msg","报名成功");

        //邮箱通知报名比赛以及项目
        List<String> events = new ArrayList<String>();
        if (apply.getE222() == 1) {
            events.add("二阶");
        }
        if (apply.getE333() == 1) {
            events.add("三阶");
        }
        if (apply.getE444() == 1) {
            events.add("四阶");
        }
        if (apply.getE555() == 1) {
            events.add("五阶");
        }
        if (apply.getE333bf() == 1) {
            events.add("三盲");
        }
        if (apply.getE333oh() == 1) {
            events.add("三单");
        }
        if (apply.getE666() == 1) {
            events.add("六阶");
        }
        if (apply.getE777() == 1) {
            events.add("七阶");
        }
        if (apply.getE333fm() == 1) {
            events.add("最少步");
        }
        if (apply.getEclock() == 1) {
            events.add("魔表");
        }
        if (apply.getEminx() == 1) {
            events.add("五魔方");
        }
        if (apply.getEpyram() == 1) {
            events.add("金字塔");
        }
        if (apply.getEskewb() == 1) {
            events.add("斜转");
        }
        if (apply.getEsq1() == 1) {
            events.add("斜转");
        }
        if (apply.getE444bf() == 1) {
            events.add("四盲");
        }
        if (apply.getE555bf() == 1) {
            events.add("五盲");
        }
        if (apply.getE333mbf() == 1) {
            events.add("三阶多盲");
        }
        String email = events.toString().replace("[","").replace("]","");
        MailUtils.sendMail(playService.queryPlayerByPId(apply.getpId()).getEmail(),"您已成功报名"+adminService.getOneGame(apply.getcId()).getName()+"比赛，项目为"+email+"","您已成功报名比赛");
        //对应比赛已报名人数 +1
        oneGame.setNowNum(oneGame.getNowNum() + 1);
        playService.updateCompetion(oneGame);
        return res;
    }

    /**
     * @Author liaotao
     * @Date 17:35 2020/10/5
     * @Description 查询选手报名的项目
     * @Param [cId, pId] 比赛id 选手id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/queryEvents")
    public Map<String,Object> queryEvents(int cId,int pId) {
        Map<String,Object> res = new HashMap<String,Object>();
        //根据cid,pid查询apply信息
        Apply apply = playService.queryApplyByCidAndPid(cId, pId);
        if (apply == null) {
            res.put("type","error");
            res.put("msg","无报名信息，请确定是否报名");
            return res;
        }
        res.put("type","success");
        res.put("msg","查询成功");
        res.put("apply",apply);
        return res;
    }

    /**
     * @Author liaotao
     * @Date 19:54 2020/10/5
     * @Description 查询赛果
     * @Param [cId, event, rounds] 比赛id 项目 轮次
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/queryAllResult")
    public Map<String,Object> queryAllResult(int cId,String event,int rounds) {
        Map<String,Object> res = new HashMap<String,Object>();
        // 查询
        List<Result5> result = playService.queryAllResult(cId, event, rounds);
        if (result == null) {
            res.put("type","error");
            res.put("msg","参数错误");
            return res;
        }
        res.put("type","success");
        res.put("msg","查询成功");
        res.put("data",result);
        // 返回
        return res;
    }

    /**
     * @Author liaotao
     * @Date 20:12 2020/10/5
     * @Description
     * @Param [cId, event, rounds, pId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/queryMyResult")
    public Map<String,Object> queryMyResult(int cId,String event,int rounds,int pId) {
        Map<String,Object> res = new HashMap<String,Object>();
        Result5 result5 = playService.queryMyResult(cId, event, rounds, pId);
        if (result5 == null) {
            res.put("type","error");
            res.put("msg","参数错误");
            return res;
        }
        res.put("type","success");
        res.put("msg","查询成功");
        res.put("data",result5);
        return res;
    }


    /**
     * @Author liaotao
     * @Date 16:07 2020/10/11
     * @Description 补报项目
     * @Param [apply]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/addApply")
    public Map<String,Object> addApply(Apply apply) {
        Map<String,Object> res = new HashMap<String,Object>();
        //根据apply获得数据库里的apply信息
        Apply apply1 = playService.findApplyById(apply.getaId());
        if (apply1.getE222() == 0 && apply.getE222() == 1 ) {
            //添加项目
            apply1.setE222(1);
        }
        if (apply1.getE333() == 0 && apply.getE333() == 1 ) {
            //添加项目
            apply1.setE333(1);
        }
        if (apply1.getE444() == 0 && apply.getE444() == 1 ) {
            //添加项目
            apply1.setE444(1);
        }
        if (apply1.getE555() == 0 && apply.getE555() == 1 ) {
            //添加项目
            apply1.setE555(1);
        }
        if (apply1.getE666() == 0 && apply.getE666() == 1 ) {
            //添加项目
            apply1.setE666(1);
        }
        if (apply1.getE777() == 0 && apply.getE777() == 1 ) {
            //添加项目
            apply1.setE777(1);
        }
        if (apply1.getE333oh() == 0 && apply.getE333oh() == 1 ) {
            //添加项目
            apply1.setE333oh(1);
        }
        if (apply1.getE333bf() == 0 && apply.getE333bf() == 1 ) {
            //添加项目
            apply1.setE333bf(1);
        }
        if (apply1.getE333fm() == 0 && apply.getE333fm() == 1 ) {
            //添加项目
            apply1.setE333fm(1);
        }
        if (apply1.getEminx() == 0 && apply.getEminx() == 1 ) {
            //添加项目
            apply1.setEminx(1);
        }
        if (apply1.getEpyram() == 0 && apply.getEpyram() == 1 ) {
            //添加项目
            apply1.setEpyram(1);
        }
        if (apply1.getEclock() == 0 && apply.getEclock() == 1 ) {
            //添加项目
            apply1.setEclock(1);
        }
        if (apply1.getEskewb() == 0 && apply.getEskewb() == 1 ) {
            //添加项目
            apply1.setEskewb(1);
        }
        if (apply1.getEsq1() == 0 && apply.getEsq1() == 1 ) {
            //添加项目
            apply1.setEsq1(1);
        }
        if (apply1.getE444bf() == 0 && apply.getE444bf() == 1 ) {
            //添加项目
            apply1.setE444bf(1);
        }
        if (apply1.getE555bf() == 0 && apply.getE555bf() == 1 ) {
            //添加项目
            apply1.setE555bf(1);
        }
        if (apply1.getE333mbf() == 0 && apply.getE333mbf() == 1 ) {
            //添加项目
            apply1.setE333mbf(1);
        }
        //apply1属性依次与apply比较 替换
        playService.updateApply(apply1);
        res.put("msg","成功");
        res.put("type","success");
        //更新报名信息
        return res;
    }

}
