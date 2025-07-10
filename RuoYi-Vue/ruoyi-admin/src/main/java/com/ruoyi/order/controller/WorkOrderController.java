package com.ruoyi.order.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.order.domain.WorkOrder;
import com.ruoyi.order.service.IWorkOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工单记录Controller
 * 
 * @author wyh
 * @date 2025-06-26
 */
@RestController
@RequestMapping("/order/order")
public class WorkOrderController extends BaseController
{
    @Autowired
    private IWorkOrderService workOrderService;

    /**
     * 查询工单记录列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkOrder workOrder)
    {
        startPage();
        List<WorkOrder> list = workOrderService.selectWorkOrderList(workOrder);
        return getDataTable(list);
    }

    /**
     * 导出工单记录列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:export')")
    @Log(title = "工单记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkOrder workOrder)
    {
        List<WorkOrder> list = workOrderService.selectWorkOrderList(workOrder);
        ExcelUtil<WorkOrder> util = new ExcelUtil<WorkOrder>(WorkOrder.class);
        util.exportExcel(response, list, "工单记录数据");
    }

    /**
     * 获取工单记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(workOrderService.selectWorkOrderById(id));
    }

    /**
     * 新增工单记录
     */
    @PreAuthorize("@ss.hasPermi('order:order:add')")
    @Log(title = "工单记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkOrder workOrder)
    {
        return toAjax(workOrderService.insertWorkOrder(workOrder));
    }

    /**
     * 修改工单记录
     */
    @PreAuthorize("@ss.hasPermi('order:order:edit')")
    @Log(title = "工单记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkOrder workOrder)
    {
        return toAjax(workOrderService.updateWorkOrder(workOrder));
    }

    /**
     * 删除工单记录
     */
    @PreAuthorize("@ss.hasPermi('order:order:remove')")
    @Log(title = "工单记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(workOrderService.deleteWorkOrderByIds(ids));
    }


    /**
     * 获取技术员列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:list')")
    @GetMapping("/technicians")
    public AjaxResult getTechnicians() {
        List<String> technicians = workOrderService.getTechnicians();
        return success(technicians);
    }

    /**
     * 获取业务员列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:list')")
    @GetMapping("/salesmen")
    public AjaxResult getSalesmen() {
        List<String> salesmen = workOrderService.getSalesmen();
        return success(salesmen);
    }
}
