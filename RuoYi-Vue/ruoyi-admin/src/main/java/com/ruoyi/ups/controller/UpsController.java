package com.ruoyi.ups.controller;

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
import com.ruoyi.ups.domain.Ups;
import com.ruoyi.ups.service.IUpsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * ups电源Controller
 * 
 * @author wyh
 * @date 2025-06-19
 */
@RestController
@RequestMapping("/ups/ups")
public class UpsController extends BaseController
{
    @Autowired
    private IUpsService upsService;

    /**
     * 查询ups电源列表
     */
    @PreAuthorize("@ss.hasPermi('ups:ups:list')")
    @GetMapping("/list")
    public TableDataInfo list(Ups ups)
    {
        startPage();
        List<Ups> list = upsService.selectUpsList(ups);
        return getDataTable(list);
    }

    /**
     * 导出ups电源列表
     */
    @PreAuthorize("@ss.hasPermi('ups:ups:export')")
    @Log(title = "ups电源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Ups ups)
    {
        List<Ups> list = upsService.selectUpsList(ups);
        ExcelUtil<Ups> util = new ExcelUtil<Ups>(Ups.class);
        util.exportExcel(response, list, "ups电源数据");
    }

    /**
     * 获取ups电源详细信息
     */
    @PreAuthorize("@ss.hasPermi('ups:ups:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(upsService.selectUpsById(id));
    }

    /**
     * 新增ups电源
     */
    @PreAuthorize("@ss.hasPermi('ups:ups:add')")
    @Log(title = "ups电源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Ups ups)
    {
        return toAjax(upsService.insertUps(ups));
    }

    /**
     * 修改ups电源
     */
    @PreAuthorize("@ss.hasPermi('ups:ups:edit')")
    @Log(title = "ups电源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Ups ups)
    {
        return toAjax(upsService.updateUps(ups));
    }

    /**
     * 删除ups电源
     */
    @PreAuthorize("@ss.hasPermi('ups:ups:remove')")
    @Log(title = "ups电源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(upsService.deleteUpsByIds(ids));
    }

    @PreAuthorize("@ss.hasPermi('ups:ups:list')")
    @GetMapping("/inputVoltagePhaseList")
    public AjaxResult getInputVoltagePhaseList() {
        List<String> list = upsService.selectDistinctInputVoltagePhase();
        return success(list);
    }

    @PreAuthorize("@ss.hasPermi('ups:ups:list')")
    @GetMapping("/outputVoltagePhaseList")
    public AjaxResult getOutputVoltagePhaseList() {
        List<String> list = upsService.selectDistinctOutputVoltagePhase();
        return success(list);
    }
}
