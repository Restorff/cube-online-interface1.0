package cn.lstf666.cube.service.impl;

import cn.lstf666.cube.mapper.AdminMapper;
import cn.lstf666.cube.model.*;
import cn.lstf666.cube.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @program: cube-online
 * @description: 管理员service实现类
 * @author: Restorff
 * @create: 2020-10-04 12:12
 **/

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean queryAdminByUsername(String username) {

        //如果数据库已经存在该用户名的管理员, 返回true
        if (adminMapper.queryAdminByUsername(username) != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean queryAdminByEmail(String email) {
        //如果数据库已经存在该用户名的管理员, 返回true
        if (adminMapper.queryAdminByEmail(email) != null)
            return true;
        else
            return false;

    }

    @Override
    public int register(Admin admin) { return adminMapper.register(admin);    }

    @Override
    public Admin login(String username, String password) { return adminMapper.login(username, password); }

    @Override
    public List<Competition> checkGame() { return adminMapper.checkGame(); }

    @Override
    public Competition getOneGame(Integer cId) { return adminMapper.getOneGame(cId); }

    @Override
    public int addAnResult5( Result5 result5) {
        result5.count();
        // 1 先判断姓名和id是不是空，空的就找一下，至少有一项有值，前台已经判断
        if("".equals(result5.getName())) {

            String nameById = adminMapper.getNameById(result5.getpId());
            result5.setName(nameById);
        }else if(result5.getpId() == 0){
            int Id = adminMapper.getIdByName(result5.getName());
            result5.setpId(Id);
        }
        // 查询这个人这个项目轮次是否已经有成绩，两种情况 1add 2 change
        Result5 result5_1 = adminMapper.checkScore(result5.getpId(), result5.getEvent(), result5.getRounds());
        if (result5_1 != null && result5.getpId()==result5_1.getpId()){
            //1 不是null说明有值 id不相等说明不是修改而是添加，返回0说明该人已存在不能重复添加
            return 0;
        }
//
//        //2 修改一条成绩时pid相等，应该先删除掉旧的，然后计算新的平均和单次
//        if(result5.getpId()==result5_1.getpId() ){
//            return 0;
//        }
        try{
            int i = adminMapper.addAnResult5(result5);
            return i;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public int changeAnResult5(Result5 result5) {
        result5.count();
        return adminMapper.changeAnResult5(result5);    }

    @Override
    public int deleteAnResult5(int rId) {    return adminMapper.deleteAnResult5(rId);    }

    @Override
    public List<Integer> getIds(Integer cId) {
        //使用hashset去重
        List<Integer> ids = new ArrayList<Integer>(new LinkedHashSet(adminMapper.getIds(cId)));
        return ids;
    }

    @Override
    public Player getOnePlayer(int pId) { return adminMapper.getOnePlayer(pId);   }

    @Override
    public List<Result5> getResult5List(int cId,int pId) {
        return adminMapper.getResult5List(cId,pId);
    }

    @Override
    public List getScores(int cId, String event,int rounds) {
        return adminMapper.getScores(cId,event,rounds);
    }

    @Override
    public int getRounds(int cId, String event) {
        return adminMapper.getRounds(cId,event);
    }

    @Override
    public Result5 getOneScore(int rId) {
        return adminMapper.getOneScore(rId);
    }

    @Override
    public Apply getApplyList(int cId, int pId) {
        System.out.println("cId="+cId+",pId="+pId);
        Apply apply = adminMapper.getApplyList(cId,pId);
//        System.out.println(apply);
//        System.out.println(apply.toString());
        //  防止前台报null的错误
        if(apply == null){
            apply = new Apply();
            apply.setcId(0);
            apply.setpId(pId);
            apply.setcId(cId);
        }
        return apply;
    }
}
