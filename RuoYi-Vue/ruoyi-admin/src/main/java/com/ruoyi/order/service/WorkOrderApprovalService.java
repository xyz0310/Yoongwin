package com.ruoyi.order.service;


import com.ruoyi.order.domain.WorkOrderApproval;
import java.util.List;



public interface WorkOrderApprovalService {
    int applyForDeletion(Long workOrderId, Long applicantId);
    int approveDeletion(Long approvalId, Long approverId);
    int rejectDeletion(Long approvalId, Long approverId);
    List<WorkOrderApproval> getApprovalList(WorkOrderApproval approval);
}
