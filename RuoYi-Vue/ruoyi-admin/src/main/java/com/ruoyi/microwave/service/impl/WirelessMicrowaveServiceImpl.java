package com.ruoyi.microwave.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.microwave.mapper.WirelessMicrowaveMapper;
import com.ruoyi.microwave.domain.WirelessMicrowave;
import com.ruoyi.microwave.service.IWirelessMicrowaveService;

/**
 * 无线微波器械Service业务层处理
 * 
 * @author wyh
 * @date 2025-06-20
 */
@Service
public class WirelessMicrowaveServiceImpl implements IWirelessMicrowaveService 
{
    @Autowired
    private WirelessMicrowaveMapper wirelessMicrowaveMapper;

    /**
     * 查询无线微波器械
     * 
     * @param id 无线微波器械主键
     * @return 无线微波器械
     */
    @Override
    public WirelessMicrowave selectWirelessMicrowaveById(Long id)
    {
        return wirelessMicrowaveMapper.selectWirelessMicrowaveById(id);
    }

    /**
     * 查询无线微波器械列表
     * 
     * @param wirelessMicrowave 无线微波器械
     * @return 无线微波器械
     */
    @Override
    public List<WirelessMicrowave> selectWirelessMicrowaveList(WirelessMicrowave wirelessMicrowave)
    {
        return wirelessMicrowaveMapper.selectWirelessMicrowaveList(wirelessMicrowave);
    }

    /**
     * 新增无线微波器械
     * 
     * @param wirelessMicrowave 无线微波器械
     * @return 结果
     */
    @Override
    public int insertWirelessMicrowave(WirelessMicrowave wirelessMicrowave)
    {
        return wirelessMicrowaveMapper.insertWirelessMicrowave(wirelessMicrowave);
    }

    /**
     * 修改无线微波器械
     * 
     * @param wirelessMicrowave 无线微波器械
     * @return 结果
     */
    @Override
    public int updateWirelessMicrowave(WirelessMicrowave wirelessMicrowave)
    {
        return wirelessMicrowaveMapper.updateWirelessMicrowave(wirelessMicrowave);
    }

    /**
     * 批量删除无线微波器械
     * 
     * @param ids 需要删除的无线微波器械主键
     * @return 结果
     */
    @Override
    public int deleteWirelessMicrowaveByIds(Long[] ids)
    {
        return wirelessMicrowaveMapper.deleteWirelessMicrowaveByIds(ids);
    }

    /**
     * 删除无线微波器械信息
     * 
     * @param id 无线微波器械主键
     * @return 结果
     */
    @Override
    public int deleteWirelessMicrowaveById(Long id)
    {
        return wirelessMicrowaveMapper.deleteWirelessMicrowaveById(id);
    }
}
