package com.ruoyi.opticalFiber.mapper;

import java.util.List;
import com.ruoyi.opticalFiber.domain.opticalCommunications;

/**
 * 光纤光缆Mapper接口
 * 
 * @author wyh
 * @date 2025-06-20
 */
public interface opticalCommunicationsMapper 
{
    /**
     * 查询光纤光缆
     * 
     * @param id 光纤光缆主键
     * @return 光纤光缆
     */
    public opticalCommunications selectopticalCommunicationsById(Long id);

    /**
     * 查询光纤光缆列表
     * 
     * @param opticalCommunications 光纤光缆
     * @return 光纤光缆集合
     */
    public List<opticalCommunications> selectopticalCommunicationsList(opticalCommunications opticalCommunications);

    /**
     * 新增光纤光缆
     * 
     * @param opticalCommunications 光纤光缆
     * @return 结果
     */
    public int insertopticalCommunications(opticalCommunications opticalCommunications);

    /**
     * 修改光纤光缆
     * 
     * @param opticalCommunications 光纤光缆
     * @return 结果
     */
    public int updateopticalCommunications(opticalCommunications opticalCommunications);

    /**
     * 删除光纤光缆
     * 
     * @param id 光纤光缆主键
     * @return 结果
     */
    public int deleteopticalCommunicationsById(Long id);

    /**
     * 批量删除光纤光缆
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteopticalCommunicationsByIds(Long[] ids);
}
