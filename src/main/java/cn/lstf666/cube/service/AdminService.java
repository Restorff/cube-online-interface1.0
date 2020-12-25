package cn.lstf666.cube.service;

import cn.lstf666.cube.model.*;

import java.util.List;

public interface AdminService {


    /**
     * 功能描述: 根据用户名查询admin是否已经存在
     *
     * @Param: username
     * @return: 如果已经存在返回true
     * @Author: Restorff
     * @Date: 2020/10/4 11:29
    */
    boolean queryAdminByUsername(String username);



    /**
     * 功能描述: 根据邮箱查询admin是否已经存在
     *
     * @Param: email
     * @return: 存在返回true
     * @Author: Restorff
     * @Date: 2020/10/4 11:38
    */
    boolean queryAdminByEmail(String email);


    /**
     * 功能描述:
     *
     * @Param: admin
     * @return: 注册成功返回1，失败返回0
     * @Author: Restorff
     * @Date: 2020/10/4 11:42
    */
    int register(Admin admin);


    /**
     * 功能描述: 管理员登录
     *
     * @Param: username,password
     * @return: admin
     * @Author: Restorff
     * @Date: 2020/10/4 14:45
    */
    Admin login(String username, String password);


    /**
     * 功能描述: 查看目前正在举行的比赛id和name
     *
     * @Param:
     * @return: Competition
     * @Author: Restorff
     * @Date: 2020/10/4 15:39
    */
    List<Competition> checkGame();


    /**
     * 功能描述: 得到一场比赛对象
     *
     * @Param: cid
     * @return: 一场比赛对象
     * @Author: Restorff
     * @Date: 2020/10/4 16:41
    */
    Competition getOneGame(Integer cId);


    /**
     * 功能描述:
     *
     * @Param: an apply
     * @return: 添加成功返回1，失败返回0
     * @Author: Restorff
     * @Date: 2020/10/5 20:27
    */
    int addAnResult5(Result5 result5);


    /**
     * 功能描述: 修改一条成绩
     *
     * @Param: result
     * @return: int
     * @Author: Restorff
     * @Date: 2020/10/6 18:33
    */
    int changeAnResult5(Result5 result5);


    /**
     * 功能描述: 删除一条成绩
     *
     * @Param: result
     * @return: int
     * @Author: Restorff
     * @Date: 2020/10/6 18:34
    */
    int deleteAnResult5(int rId);


//
    /**
     * 功能描述:  得到当前比赛所有人的id
     *
     * @Param: cid
     * @return: list<id>
     * @Author: Restorff
     * @Date: 2020/10/6 21:50
    */
    List<Integer> getIds(Integer cId);


    /**
     * 功能描述: 根据选手id拿到选手的注册信息
     *
     * @Param: pid
     * @return: player
     * @Author: Restorff
     * @Date: 2020/10/6 22:04
    */
    Player getOnePlayer(int pId);


    /**
     * 功能描述:
     *
     * @Param:
     * @return:
     * @Author: Restorff
     * @Date: 2020/10/6 22:46
    */
    List<Result5> getResult5List(int cId,int pId);

    List getScores(int cId, String event,int rounds);

    int getRounds(int cId, String event);

    Result5 getOneScore(int rId);

    Apply getApplyList(int cId, int pId);
}
