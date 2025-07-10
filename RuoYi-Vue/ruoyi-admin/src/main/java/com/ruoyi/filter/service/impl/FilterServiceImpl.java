package com.ruoyi.filter.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.filter.mapper.FilterMapper;
import com.ruoyi.filter.domain.Filter;
import com.ruoyi.filter.service.IFilterService;

/**
 * 滤波器Service业务层处理
 * 
 * @author wyh
 * @date 2025-06-20
 */
@Service
public class FilterServiceImpl implements IFilterService 
{
    @Autowired
    private FilterMapper filterMapper;

    /**
     * 查询滤波器
     * 
     * @param id 滤波器主键
     * @return 滤波器
     */
    @Override
    public Filter selectFilterById(Long id)
    {
        return filterMapper.selectFilterById(id);
    }

    /**
     * 查询滤波器列表
     * 
     * @param filter 滤波器
     * @return 滤波器
     */
    @Override
    public List<Filter> selectFilterList(Filter filter)
    {
        return filterMapper.selectFilterList(filter);
    }

    /**
     * 新增滤波器
     * 
     * @param filter 滤波器
     * @return 结果
     */
    @Override
    public int insertFilter(Filter filter)
    {
        return filterMapper.insertFilter(filter);
    }

    /**
     * 修改滤波器
     * 
     * @param filter 滤波器
     * @return 结果
     */
    @Override
    public int updateFilter(Filter filter)
    {
        return filterMapper.updateFilter(filter);
    }

    /**
     * 批量删除滤波器
     * 
     * @param ids 需要删除的滤波器主键
     * @return 结果
     */
    @Override
    public int deleteFilterByIds(Long[] ids)
    {
        return filterMapper.deleteFilterByIds(ids);
    }

    /**
     * 删除滤波器信息
     * 
     * @param id 滤波器主键
     * @return 结果
     */
    @Override
    public int deleteFilterById(Long id)
    {
        return filterMapper.deleteFilterById(id);
    }
}
