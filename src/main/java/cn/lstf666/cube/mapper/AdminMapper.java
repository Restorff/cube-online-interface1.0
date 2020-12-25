package cn.lstf666.cube.mapper;

import cn.lstf666.cube.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: cube-online
 * @description: 管理员Mapper
 * @author: Restorff
 * @create: 2020-10-04 13:17
 **/
@Repository
@Mapper
public interface AdminMapper {
   /**
    * 功能描述: 根据用户名查询管理员
    *
    * @Param: username
    * @return: admin
    * @Author: Restorff
    * @Date: 2020/10/4 13:49
   */
    Admin queryAdminByUsername(String username);


    /**
     * 功能描述:
     *
     * @Param: email
     * @return: 根据email查询是否已经存在
     * @Author: Restorff
     * @Date: 2020/10/4 14:09
    */
    Admin queryAdminByEmail(String email);

    /**
     * 功能描述: 管理员注册
     *
     * @Param: admin
     * @return: int
     * @Author: Restorff
     * @Date: 2020/10/4 14:19
    */
    int register(Admin admin);

    /**
     * 功能描述:
     *
     * @Param: username,password
     * @return: admin
     * @Author: Restorff
     * @Date: 2020/10/4 14:51
    */
    Admin login(String username, String password);


    /**
     * 功能描述: 查看当前的正在举办的比赛
     *
     * @Param:
     * @return: 比赛数组
     * @Author: Restorff
     * @Date: 2020/10/4 15:50
    */
    List<Competition> checkGame();


    /**
     * 功能描述: 得到一场比赛对象
     *
     * @Param: cid
     * @return: Competition
     * @Author: Restorff
     * @Date: 2020/10/4 16:44
    */
    Competition getOneGame(Integer cId);


    /**
     * 功能描述:插入一条成绩
     *
     * @Param: result
     * @return: 1-成功  0  -  失败
     * @Author: Restorff
     * @Date: 2020/10/6 16:31
    */
     int addAnResult5(Result5 result5);


     /**
      * 功能描述: 更细一条成绩
      *
      * @Param: result5
      * @return:
      * @Author: Restorff
      * @Date: 2020/10/6 17:12
     */
     int changeAnResult5(Result5 result5);


     /**
      * 功能描述: 删除一条成绩
      *
      * @Param:
      * @return:
      * @Author: Restorff
      * @Date: 2020/10/6 18:35
     */
      int deleteAnResult5(int rId);


      /**
       * 功能描述: 得到当前比赛的额所有人的id
       *
       * @Param:
       * @return:
       * @Author: Restorff
       * @Date: 2020/10/6 21:48
      */
      List<Integer> getIds(int cId);

      /**
       *功能描述: 根据PId拿到选手的注册信息
       *
       * @Param: pid
       * @return: player
       * @Author: Restorff
       * @Date: 2020/10/6 22:06
      */
      Player getOnePlayer(int pId);

      List<Result5> getResult5List(int cId,int pId);

      List<Result5> getScores(int cId, String event,int rounds);

      int getRounds(int cId, String event);

      String getNameById(int pId);

      int getIdByName(String name);

     Result5 checkScore(int pId, String event, int rounds);

     Result5 getOneScore(int rId);

     Apply getApplyList(int cId, int pId);
}
