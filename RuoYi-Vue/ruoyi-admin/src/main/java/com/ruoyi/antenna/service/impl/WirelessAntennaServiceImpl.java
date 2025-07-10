package com.ruoyi.antenna.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.antenna.mapper.WirelessAntennaMapper;
import com.ruoyi.antenna.domain.WirelessAntenna;
import com.ruoyi.antenna.service.IWirelessAntennaService;

/**
 * 无线天线Service业务层处理
 * 
 * @author wyh
 * @date 2025-07-01
 */
@Service
public class WirelessAntennaServiceImpl implements IWirelessAntennaService 
{
    @Autowired
    private WirelessAntennaMapper wirelessAntennaMapper;

    /**
     * 查询无线天线
     * 
     * @param id 无线天线主键
     * @return 无线天线
     */
    @Override
    public WirelessAntenna selectWirelessAntennaById(Long id)
    {
        return wirelessAntennaMapper.selectWirelessAntennaById(id);
    }

    /**
     * 查询无线天线列表
     * 
     * @param wirelessAntenna 无线天线
     * @return 无线天线
     */
    @Override
    public List<WirelessAntenna> selectWirelessAntennaList(WirelessAntenna wirelessAntenna)
    {
        return wirelessAntennaMapper.selectWirelessAntennaList(wirelessAntenna);
    }

    /**
     * 新增无线天线
     * 
     * @param wirelessAntenna 无线天线
     * @return 结果
     */
    @Override
    public int insertWirelessAntenna(WirelessAntenna wirelessAntenna)
    {
        return wirelessAntennaMapper.insertWirelessAntenna(wirelessAntenna);
    }

    /**
     * 修改无线天线
     * 
     * @param wirelessAntenna 无线天线
     * @return 结果
     */
    @Override
    public int updateWirelessAntenna(WirelessAntenna wirelessAntenna)
    {
        return wirelessAntennaMapper.updateWirelessAntenna(wirelessAntenna);
    }

    /**
     * 批量删除无线天线
     * 
     * @param ids 需要删除的无线天线主键
     * @return 结果
     */
    @Override
    public int deleteWirelessAntennaByIds(Long[] ids)
    {
        return wirelessAntennaMapper.deleteWirelessAntennaByIds(ids);
    }

    /**
     * 删除无线天线信息
     * 
     * @param id 无线天线主键
     * @return 结果
     */
    @Override
    public int deleteWirelessAntennaById(Long id)
    {
        return wirelessAntennaMapper.deleteWirelessAntennaById(id);
    }

    /**
     * 获取所有天线种类
     * @return 天线种类列表
     */
    @Override
    public List<String> selectAllAntennaTypes() { return wirelessAntennaMapper.selectAllAntennaTypes(); }
    /**
     * 获取所有室内外类型
     * @return 室内外类型列表
     */
    @Override
    public List<String> selectAllIndoorOutdoorTypes() {
        return wirelessAntennaMapper.selectAllIndoorOutdoorTypes();
    }
    /**
     * 获取所有全定向类型
     * @return 全定向类型列表
     */
    @Override
    public List<String> selectAllOmniDirectionalTypes() {
        return wirelessAntennaMapper.selectAllOmniDirectionalTypes();
    }
    /**
     * 获取所有极化方式类型
     * @return 极化方式类型列表
     */
    @Override
    public List<String> selectAllPolarizationTypes() {
        return wirelessAntennaMapper.selectAllPolarizationTypes();
    }
}
