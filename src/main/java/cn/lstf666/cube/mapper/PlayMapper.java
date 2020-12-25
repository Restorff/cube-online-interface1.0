package cn.lstf666.cube.mapper;

import cn.lstf666.cube.model.Apply;
import cn.lstf666.cube.model.Competition;
import cn.lstf666.cube.model.Player;
import cn.lstf666.cube.model.Result5;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author liaotao
 * @Date 2020/10/3 22:25
 */
@Component
@Mapper
public interface PlayMapper {
    /**
     * @Author liaotao
     * @Date 22:21 2020/10/3
     * @Description  根据用户名查询选手信息
     * @Param [username] 用户名
     * @return cn.lstf666.cube.model.Player 选手对象
     **/
    public Player queryPlayerByUsername(String username);

    /**
     * @Author liaotao
     * @Date 22:22 2020/10/3
     * @Description  根据邮箱查询选手信息
     * @Param [email] 邮箱
     * @return cn.lstf666.cube.model.Player 选手对象
     **/
    public Player queryPlayerByEmail(String email);

    /**
     * @Author liaotao
     * @Date 22:24 2020/10/3
     * @Description 选手注册
     * @Param [player] 选手对象
     * @return int 注册成功返回 1
     **/
    public int register(Player player);

    /**
     * @Author liaotao
     * @Date 22:58 2020/10/3
     * @Description 根据激活码查询选手
     * @Param [code]
     * @return cn.lstf666.cube.model.Player
     **/
    public Player queryPlayerByCode(String code);

    /**
     * @Author liaotao
     * @Date 23:01 2020/10/3
     * @Description 激活选手
     * @Param [player]
     * @return int
     **/
    public int active(int pId);

    /**
     * @Author liaotao
     * @Date 15:30 2020/10/5
     * @Description 查询选手报名信息
     * @Param [pId]
     * @return int
     **/
    public List<Integer> querycIdBypId(int pId);

    /**
     * @Author liaotao
     * @Date 16:17 2020/10/5
     * @Description 报名比赛
     * @Param [apply]
     * @return int
     **/
    public int chooseEvent(Apply apply);

    /**
     * @Author liaotao
     * @Date 16:22 2020/10/5
     * @Description 更新比赛信息
     * @Param [competition]
     * @return int
     **/
    public int updateCompetion(Competition competition);

    /**
     * @Author liaotao
     * @Date 16:49 2020/10/5
     * @Description 根据id查询选手
     * @Param [pId]
     * @return cn.lstf666.cube.model.Player
     **/
    public Player queryPlayerByPId(int pId);

    /**
     * @Author liaotao
     * @Date 17:41 2020/10/5
     * @Description 根据选手id 比赛id 查询选手的报名信息
     * @Param [cId, pId]
     * @return cn.lstf666.cube.model.Apply
     **/
    public Apply queryApplyByCidAndPid(@Param("cId") int cId, @Param("pId") int pId);

    /**
     * @Author liaotao
     * @Date 19:58 2020/10/5
     * @Description 查询赛果
     * @Param [cid, event, rounds]
     * @return java.util.List<cn.lstf666.cube.model.Result5>
     **/
    public List<Result5> queryAllResult(@Param("cId") int cId, @Param("event") String event, @Param("rounds") int rounds);

    /**
     * @Author liaotao
     * @Date 20:14 2020/10/5
     * @Description 查询个人赛果
     * @Param [cId, event, rounds, pId]
     * @return cn.lstf666.cube.model.Result5
     **/
    public Result5 queryMyResult(@Param("cId") int cId,@Param("event") String event,@Param("rounds") int rounds,@Param("pId") int pId);

    /**
     * @Author liaotao
     * @Date 15:46 2020/10/11
     * @Description 根据aId查询报名信息
     * @Param [aId]
     * @return cn.lstf666.cube.model.Apply
     **/
    public Apply findApplyById(int aId);

    /**
     * @Author liaotao
     * @Date 15:51 2020/10/11
     * @Description
     * @Param [apply] 更新报名信息
     * @return int
     **/
    public int updateApply(Apply apply);

}
