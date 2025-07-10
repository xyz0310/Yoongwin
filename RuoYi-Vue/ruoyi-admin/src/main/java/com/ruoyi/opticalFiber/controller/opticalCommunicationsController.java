package com.ruoyi.opticalFiber.controller;

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
import com.ruoyi.opticalFiber.domain.opticalCommunications;
import com.ruoyi.opticalFiber.service.IopticalCommunicationsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 光纤光缆Controller
 * 
 * @author wyh
 * @date 2025-06-20
 */
@RestController
@RequestMapping("/opticalFiber/opticalFiber")
public class opticalCommunicationsController extends BaseController
{
    @Autowired
    private IopticalCommunicationsService opticalCommunicationsService;

    /**
     * 查询光纤光缆列表
     */
    @PreAuthorize("@ss.hasPermi('opticalFiber:opticalFiber:list')")
    @GetMapping("/list")
    public TableDataInfo list(opticalCommunications opticalCommunications)
    {
        startPage();
        List<opticalCommunications> list = opticalCommunicationsService.selectopticalCommunicationsList(opticalCommunications);
        return getDataTable(list);
    }

    /**
     * 导出光纤光缆列表
     */
    @PreAuthorize("@ss.hasPermi('opticalFiber:opticalFiber:export')")
    @Log(title = "光纤光缆", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, opticalCommunications opticalCommunications)
    {
        List<opticalCommunications> list = opticalCommunicationsService.selectopticalCommunicationsList(opticalCommunications);
        ExcelUtil<opticalCommunications> util = new ExcelUtil<opticalCommunications>(opticalCommunications.class);
        util.exportExcel(response, list, "光纤光缆数据");
    }

    /**
     * 获取光纤光缆详细信息
     */
    @PreAuthorize("@ss.hasPermi('opticalFiber:opticalFiber:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(opticalCommunicationsService.selectopticalCommunicationsById(id));
    }

    /**
     * 新增光纤光缆
     */
    @PreAuthorize("@ss.hasPermi('opticalFiber:opticalFiber:add')")
    @Log(title = "光纤光缆", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody opticalCommunications opticalCommunications)
    {
        return toAjax(opticalCommunicationsService.insertopticalCommunications(opticalCommunications));
    }

    /**
     * 修改光纤光缆
     */
    @PreAuthorize("@ss.hasPermi('opticalFiber:opticalFiber:edit')")
    @Log(title = "光纤光缆", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody opticalCommunications opticalCommunications)
    {
        return toAjax(opticalCommunicationsService.updateopticalCommunications(opticalCommunications));
    }

    /**
     * 删除光纤光缆
     */
    @PreAuthorize("@ss.hasPermi('opticalFiber:opticalFiber:remove')")
    @Log(title = "光纤光缆", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(opticalCommunicationsService.deleteopticalCommunicationsByIds(ids));
    }
}
