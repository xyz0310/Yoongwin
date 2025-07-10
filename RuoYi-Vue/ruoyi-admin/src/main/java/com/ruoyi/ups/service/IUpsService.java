package com.ruoyi.ups.service;

import java.util.List;
import com.ruoyi.ups.domain.Ups;

/**
 * ups电源Service接口
 * 
 * @author wyh
 * @date 2025-06-19
 */
public interface IUpsService 
{
    /**
     * 查询ups电源
     * 
     * @param id ups电源主键
     * @return ups电源
     */
    public Ups selectUpsById(Long id);

    /**
     * 查询ups电源列表
     * 
     * @param ups ups电源
     * @return ups电源集合
     */
    public List<Ups> selectUpsList(Ups ups);

    /**
     * 新增ups电源
     * 
     * @param ups ups电源
     * @return 结果
     */
    public int insertUps(Ups ups);

    /**
     * 修改ups电源
     * 
     * @param ups ups电源
     * @return 结果
     */
    public int updateUps(Ups ups);

    /**
     * 批量删除ups电源
     * 
     * @param ids 需要删除的ups电源主键集合
     * @return 结果
     */
    public int deleteUpsByIds(Long[] ids);

    /**
     * 删除ups电源信息
     * 
     * @param id ups电源主键
     * @return 结果
     */
    public int deleteUpsById(Long id);

    List<String> selectDistinctInputVoltagePhase();
    List<String> selectDistinctOutputVoltagePhase();
}
