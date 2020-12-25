package cn.lstf666.cube.service.impl;

import cn.lstf666.cube.mapper.RootMapper;
import cn.lstf666.cube.model.Admin;
import cn.lstf666.cube.model.Competition;
import cn.lstf666.cube.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author liaotao
 * @Date 2020/10/3 15:32
 */
@Service("rootService")
public class RootServiceImpl implements RootService {

    @Autowired
    private RootMapper rootMapper;

    @Override
    public int makeGame(Competition competition) {
        return rootMapper.makeGame(competition);
    }

    @Override
    public Admin queryAdminByCode(String code) {
        return rootMapper.queryAdminByCode(code);
    }

    @Override
    public int activeAdmin(int aId) {
        return rootMapper.activeAdmin(aId);
    }
}
