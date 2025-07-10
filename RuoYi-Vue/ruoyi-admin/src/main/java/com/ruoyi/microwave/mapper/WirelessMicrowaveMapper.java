package com.ruoyi.microwave.mapper;

import java.util.List;
import com.ruoyi.microwave.domain.WirelessMicrowave;

/**
 * 无线微波器械Mapper接口
 * 
 * @author wyh
 * @date 2025-06-20
 */
public interface WirelessMicrowaveMapper 
{
    /**
     * 查询无线微波器械
     * 
     * @param id 无线微波器械主键
     * @return 无线微波器械
     */
    public WirelessMicrowave selectWirelessMicrowaveById(Long id);

    /**
     * 查询无线微波器械列表
     * 
     * @param wirelessMicrowave 无线微波器械
     * @return 无线微波器械集合
     */
    public List<WirelessMicrowave> selectWirelessMicrowaveList(WirelessMicrowave wirelessMicrowave);

    /**
     * 新增无线微波器械
     * 
     * @param wirelessMicrowave 无线微波器械
     * @return 结果
     */
    public int insertWirelessMicrowave(WirelessMicrowave wirelessMicrowave);

    /**
     * 修改无线微波器械
     * 
     * @param wirelessMicrowave 无线微波器械
     * @return 结果
     */
    public int updateWirelessMicrowave(WirelessMicrowave wirelessMicrowave);

    /**
     * 删除无线微波器械
     * 
     * @param id 无线微波器械主键
     * @return 结果
     */
    public int deleteWirelessMicrowaveById(Long id);

    /**
     * 批量删除无线微波器械
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWirelessMicrowaveByIds(Long[] ids);
}
