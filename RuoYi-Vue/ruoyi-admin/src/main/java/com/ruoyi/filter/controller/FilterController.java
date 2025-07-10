package com.ruoyi.filter.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.filter.domain.Filter;
import com.ruoyi.filter.service.IFilterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 滤波器Controller
 * 
 * @author wyh
 * @date 2025-06-20
 */
@RestController
@RequestMapping("/filter/filter")
public class FilterController extends BaseController
{
    @Autowired
    private IFilterService filterService;

    /**
     * 查询滤波器列表
     */
    @PreAuthorize("@ss.hasPermi('filter:filter:list')")
    @GetMapping("/list")
    public TableDataInfo list(Filter filter)
    {
        startPage();
        List<Filter> list = filterService.selectFilterList(filter);
        return getDataTable(list);
    }

    /**
     * 导出滤波器列表
     */
    @PreAuthorize("@ss.hasPermi('filter:filter:export')")
    @Log(title = "滤波器", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Filter filter)
    {
        List<Filter> list = filterService.selectFilterList(filter);
        ExcelUtil<Filter> util = new ExcelUtil<Filter>(Filter.class);
        util.exportExcel(response, list, "滤波器数据");
    }

    /**
     * 获取滤波器详细信息
     */
    @PreAuthorize("@ss.hasPermi('filter:filter:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(filterService.selectFilterById(id));
    }

    /**
     * 新增滤波器
     */
    @PreAuthorize("@ss.hasPermi('filter:filter:add')")
    @Log(title = "滤波器", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Filter filter)
    {
        return toAjax(filterService.insertFilter(filter));
    }

    /**
     * 修改滤波器
     */
    @PreAuthorize("@ss.hasPermi('filter:filter:edit')")
    @Log(title = "滤波器", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Filter filter)
    {
        return toAjax(filterService.updateFilter(filter));
    }

    /**
     * 删除滤波器
     */
    @PreAuthorize("@ss.hasPermi('filter:filter:remove')")
    @Log(title = "滤波器", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(filterService.deleteFilterByIds(ids));
    }
}
