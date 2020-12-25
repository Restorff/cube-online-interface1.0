package cn.lstf666.cube.service;

import cn.lstf666.cube.model.Admin;
import cn.lstf666.cube.model.Competition;
/**
 * @Author liaotao
 * @Date 2020/10/3 15:32
 */
public interface RootService {

    /**
     * @Author liaotao
     * @Date 15:38 2020/10/3
     * @Description 创建新比赛
     * @Param [competition] 比赛信息对象
     * @return int
     **/
    public int makeGame(Competition competition);

    /**
     * @Author liaotao
     * @Date 18:31 2020/10/3
     * @Description 根据激活码查询用户
     * @Param [code] 激活码
     * @return cn.lstf666.cube.model.Admin 查询不到返回null
     **/
    public Admin queryAdminByCode(String code);

    /**
     * @Author liaotao
     * @Date 21:11 2020/10/3
     * @Description 激活管理员
     * @Param [aId] 管理员id
     * @return int 激活成功返回 1
     **/
    public int activeAdmin(int aId);

}
