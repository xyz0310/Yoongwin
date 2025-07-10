package com.ruoyi.microwave.controller;

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
import com.ruoyi.microwave.domain.WirelessMicrowave;
import com.ruoyi.microwave.service.IWirelessMicrowaveService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 无线微波器械Controller
 * 
 * @author wyh
 * @date 2025-06-20
 */
@RestController
@RequestMapping("/microwave/microwave")
public class WirelessMicrowaveController extends BaseController
{
    @Autowired
    private IWirelessMicrowaveService wirelessMicrowaveService;

    /**
     * 查询无线微波器械列表
     */
    @PreAuthorize("@ss.hasPermi('microwave:microwave:list')")
    @GetMapping("/list")
    public TableDataInfo list(WirelessMicrowave wirelessMicrowave)
    {
        startPage();
        List<WirelessMicrowave> list = wirelessMicrowaveService.selectWirelessMicrowaveList(wirelessMicrowave);
        return getDataTable(list);
    }

    /**
     * 导出无线微波器械列表
     */
    @PreAuthorize("@ss.hasPermi('microwave:microwave:export')")
    @Log(title = "无线微波器械", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WirelessMicrowave wirelessMicrowave)
    {
        List<WirelessMicrowave> list = wirelessMicrowaveService.selectWirelessMicrowaveList(wirelessMicrowave);
        ExcelUtil<WirelessMicrowave> util = new ExcelUtil<WirelessMicrowave>(WirelessMicrowave.class);
        util.exportExcel(response, list, "无线微波器械数据");
    }

    /**
     * 获取无线微波器械详细信息
     */
    @PreAuthorize("@ss.hasPermi('microwave:microwave:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wirelessMicrowaveService.selectWirelessMicrowaveById(id));
    }

    /**
     * 新增无线微波器械
     */
    @PreAuthorize("@ss.hasPermi('microwave:microwave:add')")
    @Log(title = "无线微波器械", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WirelessMicrowave wirelessMicrowave)
    {
        return toAjax(wirelessMicrowaveService.insertWirelessMicrowave(wirelessMicrowave));
    }

    /**
     * 修改无线微波器械
     */
    @PreAuthorize("@ss.hasPermi('microwave:microwave:edit')")
    @Log(title = "无线微波器械", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WirelessMicrowave wirelessMicrowave)
    {
        return toAjax(wirelessMicrowaveService.updateWirelessMicrowave(wirelessMicrowave));
    }

    /**
     * 删除无线微波器械
     */
    @PreAuthorize("@ss.hasPermi('microwave:microwave:remove')")
    @Log(title = "无线微波器械", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wirelessMicrowaveService.deleteWirelessMicrowaveByIds(ids));
    }
}
