package cn.lstf666.cube.controller;

import cn.lstf666.cube.model.Admin;
import cn.lstf666.cube.model.Competition;
import cn.lstf666.cube.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author liaotao
 * @Date 2020/10/3 15:25
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/root")
public class RootController {

    @Autowired
    private RootService rootService;

    /**
     * @Author liaotao
     * @Date 17:37 2020/10/3
     * @Description 创建比赛
     * @Param [competition]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/makeGame")
    public Map<String,Object> makeGame( Competition competition) {
        Map<String,Object> res = new HashMap<String, Object>();
        int result = rootService.makeGame(competition);
        if (result == 1) {
            res.put("type","success");
            res.put("msg","创建成功");
        }else {
            res.put("type","error");
            res.put("msg","创建失败，请联系管理员");
        }
        return res;
    }

    /**
     * @Author liaotao
     * @Date 18:25 2020/10/3
     * @Description 激活管理员
     * @Param [code]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/activeAdmin")
    public void activeAdmin(String code, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        //1.根据code查询数据库中管理员信息
        Admin admin = rootService.queryAdminByCode(code);
        PrintWriter out;
        if (admin == null) {
            try {
                out = response.getWriter();
                out.print("<script>alert('所激活用户不存在!');</script>");
                out.close();
                response.getWriter().println("error,所激活用户不存在!");
            } catch (IOException e) {
                e.printStackTrace();
            }
//            return res;
        }
        //2.将该管理员激活状态设置为y
        int result = rootService.activeAdmin(admin.getaId());
        if (result == 1) {
            try {
                out = response.getWriter();
                out.print("<script>alert('激活成功!');</script>");
                out.close();
                response.getWriter().println("success,激活成功!");
            } catch (IOException e) {
                e.printStackTrace();
            }
//            res.put("type","");
//            res.put("msg","激活成功");
        }else {
            try {
                out = response.getWriter();
                out.print("<script>alert('激活失败，请联系站长!!');</script>");
                out.close();
                response.getWriter().println("error,激活失败，请联系站长!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        return res;
    }
}

