package com.ruoyi.antenna.mapper;

import java.util.List;
import com.ruoyi.antenna.domain.WirelessAntenna;

/**
 * 无线天线Mapper接口
 * 
 * @author wyh
 * @date 2025-07-01
 */
public interface WirelessAntennaMapper 
{
    /**
     * 查询无线天线
     * 
     * @param id 无线天线主键
     * @return 无线天线
     */
    public WirelessAntenna selectWirelessAntennaById(Long id);

    /**
     * 查询无线天线列表
     * 
     * @param wirelessAntenna 无线天线
     * @return 无线天线集合
     */
    public List<WirelessAntenna> selectWirelessAntennaList(WirelessAntenna wirelessAntenna);

    /**
     * 新增无线天线
     * 
     * @param wirelessAntenna 无线天线
     * @return 结果
     */
    public int insertWirelessAntenna(WirelessAntenna wirelessAntenna);

    /**
     * 修改无线天线
     * 
     * @param wirelessAntenna 无线天线
     * @return 结果
     */
    public int updateWirelessAntenna(WirelessAntenna wirelessAntenna);

    /**
     * 删除无线天线
     * 
     * @param id 无线天线主键
     * @return 结果
     */
    public int deleteWirelessAntennaById(Long id);

    /**
     * 批量删除无线天线
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWirelessAntennaByIds(Long[] ids);

    /**
     * 获取所有天线种类
     * @return 天线种类列表
     */
    public List<String> selectAllAntennaTypes();

    /**
     * 获取所有室内外类型
     * @return 室内外类型列表
     */
    public List<String> selectAllIndoorOutdoorTypes();

    /**
     * 获取所有全定向类型
     * @return 全定向类型列表
     */
    public List<String> selectAllOmniDirectionalTypes();

    /**
     * 获取所有极化方式类型
     * @return 极化方式类型列表
     */
    public List<String> selectAllPolarizationTypes();
}
