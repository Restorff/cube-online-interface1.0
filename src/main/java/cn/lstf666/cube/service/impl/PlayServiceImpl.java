package cn.lstf666.cube.service.impl;

import cn.lstf666.cube.mapper.PlayMapper;
import cn.lstf666.cube.model.Apply;
import cn.lstf666.cube.model.Competition;
import cn.lstf666.cube.model.Player;
import cn.lstf666.cube.model.Result5;
import cn.lstf666.cube.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author liaotao
 * @Date 2020/10/3 22:25
 */
@Service("playService")
public class PlayServiceImpl implements PlayService {

    @Autowired
    private PlayMapper playMapper;

    @Override
    public Player queryPlayerByUsername(String username) {
        return playMapper.queryPlayerByUsername(username);
    }

    @Override
    public Player queryPlayerByEmail(String email) {
        return playMapper.queryPlayerByEmail(email);
    }

    @Override
    public int register(Player player) {
        return playMapper.register(player);
    }

    @Override
    public Player queryPlayerByCode(String code) {
        return playMapper.queryPlayerByCode(code);
    }

    @Override
    public int active(int pId) {
        return playMapper.active(pId);
    }

    @Override
    public List<Integer> querycIdBypId(int pId) {
        return playMapper.querycIdBypId(pId);
    }

    @Override
    public int chooseEvent(Apply apply) {
        return playMapper.chooseEvent(apply);
    }

    @Override
    public int updateCompetion(Competition competition) {
        return playMapper.updateCompetion(competition);
    }

    @Override
    public Player queryPlayerByPId(int pId) {
        return playMapper.queryPlayerByPId(pId);
    }

    @Override
    public Apply queryApplyByCidAndPid(int cId, int pId) {
        return playMapper.queryApplyByCidAndPid(cId,pId);
    }

    @Override
    public List<Result5> queryAllResult(int cId, String event, int rounds) {
        return playMapper.queryAllResult(cId,event,rounds);
    }

    @Override
    public Result5 queryMyResult(int cId, String event, int rounds, int pId) {
        return playMapper.queryMyResult(cId,event,rounds,pId);
    }

    @Override
    public Apply findApplyById(int aId) {
        return playMapper.findApplyById(aId);
    }

    @Override
    public int updateApply(Apply apply) {
        return playMapper.updateApply(apply);
    }
}
