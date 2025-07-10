package com.ruoyi.ups.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ups.mapper.UpsMapper;
import com.ruoyi.ups.domain.Ups;
import com.ruoyi.ups.service.IUpsService;

/**
 * ups电源Service业务层处理
 * 
 * @author wyh
 * @date 2025-06-19
 */
@Service
public class UpsServiceImpl implements IUpsService 
{
    @Autowired
    private UpsMapper upsMapper;

    /**
     * 查询ups电源
     * 
     * @param id ups电源主键
     * @return ups电源
     */
    @Override
    public Ups selectUpsById(Long id)
    {
        return upsMapper.selectUpsById(id);
    }

    /**
     * 查询ups电源列表
     * 
     * @param ups ups电源
     * @return ups电源
     */
    @Override
    public List<Ups> selectUpsList(Ups ups)
    {
        return upsMapper.selectUpsList(ups);
    }

    /**
     * 新增ups电源
     * 
     * @param ups ups电源
     * @return 结果
     */
    @Override
    public int insertUps(Ups ups)
    {
        return upsMapper.insertUps(ups);
    }

    /**
     * 修改ups电源
     * 
     * @param ups ups电源
     * @return 结果
     */
    @Override
    public int updateUps(Ups ups)
    {
        return upsMapper.updateUps(ups);
    }

    /**
     * 批量删除ups电源
     * 
     * @param ids 需要删除的ups电源主键
     * @return 结果
     */
    @Override
    public int deleteUpsByIds(Long[] ids)
    {
        return upsMapper.deleteUpsByIds(ids);
    }

    /**
     * 删除ups电源信息
     * 
     * @param id ups电源主键
     * @return 结果
     */
    @Override
    public int deleteUpsById(Long id)
    {
        return upsMapper.deleteUpsById(id);
    }

    @Override
    public List<String> selectDistinctInputVoltagePhase() {
        return upsMapper.selectDistinctInputVoltagePhase();
    }

    @Override
    public List<String> selectDistinctOutputVoltagePhase() {
        return upsMapper.selectDistinctOutputVoltagePhase();
    }
}
