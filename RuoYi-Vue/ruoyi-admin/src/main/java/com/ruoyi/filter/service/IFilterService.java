package com.ruoyi.filter.service;

import java.util.List;
import com.ruoyi.filter.domain.Filter;

/**
 * 滤波器Service接口
 * 
 * @author wyh
 * @date 2025-06-20
 */
public interface IFilterService 
{
    /**
     * 查询滤波器
     * 
     * @param id 滤波器主键
     * @return 滤波器
     */
    public Filter selectFilterById(Long id);

    /**
     * 查询滤波器列表
     * 
     * @param filter 滤波器
     * @return 滤波器集合
     */
    public List<Filter> selectFilterList(Filter filter);

    /**
     * 新增滤波器
     * 
     * @param filter 滤波器
     * @return 结果
     */
    public int insertFilter(Filter filter);

    /**
     * 修改滤波器
     * 
     * @param filter 滤波器
     * @return 结果
     */
    public int updateFilter(Filter filter);

    /**
     * 批量删除滤波器
     * 
     * @param ids 需要删除的滤波器主键集合
     * @return 结果
     */
    public int deleteFilterByIds(Long[] ids);

    /**
     * 删除滤波器信息
     * 
     * @param id 滤波器主键
     * @return 结果
     */
    public int deleteFilterById(Long id);
}
