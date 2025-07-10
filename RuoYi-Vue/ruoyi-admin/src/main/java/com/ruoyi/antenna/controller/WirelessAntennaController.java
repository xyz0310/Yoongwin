package com.ruoyi.antenna.controller;

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
import com.ruoyi.antenna.domain.WirelessAntenna;
import com.ruoyi.antenna.service.IWirelessAntennaService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 无线天线Controller
 * 
 * @author wyh
 * @date 2025-07-01
 */
@RestController
@RequestMapping("/antenna/antenna")
public class WirelessAntennaController extends BaseController
{
    @Autowired
    private IWirelessAntennaService wirelessAntennaService;

    /**
     * 查询无线天线列表
     */
    @PreAuthorize("@ss.hasPermi('antenna:antenna:list')")
    @GetMapping("/list")
    public TableDataInfo list(WirelessAntenna wirelessAntenna)
    {
        startPage();
        List<WirelessAntenna> list = wirelessAntennaService.selectWirelessAntennaList(wirelessAntenna);
        return getDataTable(list);
    }

    /**
     * 导出无线天线列表
     */
    @PreAuthorize("@ss.hasPermi('antenna:antenna:export')")
    @Log(title = "无线天线", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WirelessAntenna wirelessAntenna)
    {
        List<WirelessAntenna> list = wirelessAntennaService.selectWirelessAntennaList(wirelessAntenna);
        ExcelUtil<WirelessAntenna> util = new ExcelUtil<WirelessAntenna>(WirelessAntenna.class);
        util.exportExcel(response, list, "无线天线数据");
    }

    /**
     * 获取无线天线详细信息
     */
    @PreAuthorize("@ss.hasPermi('antenna:antenna:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wirelessAntennaService.selectWirelessAntennaById(id));
    }

    /**
     * 新增无线天线
     */
    @PreAuthorize("@ss.hasPermi('antenna:antenna:add')")
    @Log(title = "无线天线", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WirelessAntenna wirelessAntenna)
    {
        return toAjax(wirelessAntennaService.insertWirelessAntenna(wirelessAntenna));
    }

    /**
     * 修改无线天线
     */
    @PreAuthorize("@ss.hasPermi('antenna:antenna:edit')")
    @Log(title = "无线天线", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WirelessAntenna wirelessAntenna)
    {
        return toAjax(wirelessAntennaService.updateWirelessAntenna(wirelessAntenna));
    }

    /**
     * 删除无线天线
     */
    @PreAuthorize("@ss.hasPermi('antenna:antenna:remove')")
    @Log(title = "无线天线", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wirelessAntennaService.deleteWirelessAntennaByIds(ids));
    }

    /**
     * 获取所有天线种类
     */
    @PreAuthorize("@ss.hasPermi('antenna:antenna:list')")
    @GetMapping("/allAntennaTypes")
    public AjaxResult getAllAntennaTypes() {
        List<String> antennaTypes = wirelessAntennaService.selectAllAntennaTypes();
        return success(antennaTypes);
    }

    /**
     * 获取所有室内外类型
     */
    @PreAuthorize("@ss.hasPermi('antenna:antenna:list')")
    @GetMapping("/allIndoorOutdoorTypes")
    public AjaxResult getAllIndoorOutdoorTypes() {
        List<String> indoorOutdoorTypes = wirelessAntennaService.selectAllIndoorOutdoorTypes();
        return success(indoorOutdoorTypes);
    }

    /**
     * 获取所有全定向类型
     */
    @PreAuthorize("@ss.hasPermi('antenna:antenna:list')")
    @GetMapping("/allOmniDirectionalTypes")
    public AjaxResult getAllOmniDirectionalTypes() {
        List<String> omniDirectionalTypes = wirelessAntennaService.selectAllOmniDirectionalTypes();
        return success(omniDirectionalTypes);
    }

    /**
     * 获取所有极化方式类型
     */
    @PreAuthorize("@ss.hasPermi('antenna:antenna:list')")
    @GetMapping("/allPolarizationTypes")
    public AjaxResult getAllPolarizationTypes() {
        List<String> polarizationTypes = wirelessAntennaService.selectAllPolarizationTypes();
        return success(polarizationTypes);
    }
}
