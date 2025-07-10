package com.ruoyi.order.controller;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.order.domain.WorkOrderApproval;
import com.ruoyi.order.service.WorkOrderApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order/approval")
public class WorkOrderApprovalController extends BaseController {
    @Autowired
    private WorkOrderApprovalService workOrderApprovalService;

    /**
     * 申请删除工单
     */
    @PreAuthorize("@ss.hasPermi('order:order:applyDelete')")
    @PostMapping("/apply")
    public AjaxResult applyForDeletion(@RequestBody WorkOrderApproval approval) {
        return toAjax(workOrderApprovalService.applyForDeletion(approval.getWorkOrderId(), approval.getApplicantId()));
    }

    /**
     * 审批通过删除工单
     */
    @PreAuthorize("@ss.hasPermi('order:order:approveDelete')")
    @PutMapping("/approve/{approvalId}")
    public AjaxResult approveDeletion(@PathVariable Long approvalId, @RequestParam Long approverId) {
        return toAjax(workOrderApprovalService.approveDeletion(approvalId, approverId));
    }

    /**
     * 审批拒绝删除工单
     */
    @PreAuthorize("@ss.hasPermi('order:order:approveDelete')")
    @PutMapping("/reject/{approvalId}")
    public AjaxResult rejectDeletion(@PathVariable Long approvalId, @RequestParam Long approverId) {
        return toAjax(workOrderApprovalService.rejectDeletion(approvalId, approverId));
    }

    /**
     * 获取审批列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:approveList')")
    @GetMapping("/list")
    public TableDataInfo getApprovalList(WorkOrderApproval approval) {
        startPage();
        List<WorkOrderApproval> list = workOrderApprovalService.getApprovalList(approval);
        return getDataTable(list);
    }
}
