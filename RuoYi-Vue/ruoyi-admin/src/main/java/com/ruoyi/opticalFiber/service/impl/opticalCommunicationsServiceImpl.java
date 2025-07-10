package com.ruoyi.opticalFiber.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.opticalFiber.mapper.opticalCommunicationsMapper;
import com.ruoyi.opticalFiber.domain.opticalCommunications;
import com.ruoyi.opticalFiber.service.IopticalCommunicationsService;

/**
 * 光纤光缆Service业务层处理
 * 
 * @author wyh
 * @date 2025-06-20
 */
@Service
public class opticalCommunicationsServiceImpl implements IopticalCommunicationsService 
{
    @Autowired
    private opticalCommunicationsMapper opticalCommunicationsMapper;

    /**
     * 查询光纤光缆
     * 
     * @param id 光纤光缆主键
     * @return 光纤光缆
     */
    @Override
    public opticalCommunications selectopticalCommunicationsById(Long id)
    {
        return opticalCommunicationsMapper.selectopticalCommunicationsById(id);
    }

    /**
     * 查询光纤光缆列表
     * 
     * @param opticalCommunications 光纤光缆
     * @return 光纤光缆
     */
    @Override
    public List<opticalCommunications> selectopticalCommunicationsList(opticalCommunications opticalCommunications)
    {
        return opticalCommunicationsMapper.selectopticalCommunicationsList(opticalCommunications);
    }

    /**
     * 新增光纤光缆
     * 
     * @param opticalCommunications 光纤光缆
     * @return 结果
     */
    @Override
    public int insertopticalCommunications(opticalCommunications opticalCommunications)
    {
        return opticalCommunicationsMapper.insertopticalCommunications(opticalCommunications);
    }

    /**
     * 修改光纤光缆
     * 
     * @param opticalCommunications 光纤光缆
     * @return 结果
     */
    @Override
    public int updateopticalCommunications(opticalCommunications opticalCommunications)
    {
        return opticalCommunicationsMapper.updateopticalCommunications(opticalCommunications);
    }

    /**
     * 批量删除光纤光缆
     * 
     * @param ids 需要删除的光纤光缆主键
     * @return 结果
     */
    @Override
    public int deleteopticalCommunicationsByIds(Long[] ids)
    {
        return opticalCommunicationsMapper.deleteopticalCommunicationsByIds(ids);
    }

    /**
     * 删除光纤光缆信息
     * 
     * @param id 光纤光缆主键
     * @return 结果
     */
    @Override
    public int deleteopticalCommunicationsById(Long id)
    {
        return opticalCommunicationsMapper.deleteopticalCommunicationsById(id);
    }
}
