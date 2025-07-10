package com.ruoyi.order.service.impl;


import com.ruoyi.order.domain.WorkOrderApproval;
import com.ruoyi.order.mapper.WorkOrderApprovalMapper;
import com.ruoyi.order.mapper.WorkOrderMapper;
import com.ruoyi.order.service.WorkOrderApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkOrderApprovalServiceImpl implements WorkOrderApprovalService {
    @Autowired
    private WorkOrderApprovalMapper workOrderApprovalMapper;
    @Autowired
    private WorkOrderMapper workOrderMapper;

    @Override
    public int applyForDeletion(Long workOrderId, Long applicantId) {
        WorkOrderApproval approval = new WorkOrderApproval();
        approval.setWorkOrderId(workOrderId);
        approval.setApplicantId(applicantId);
        approval.setStatus("待审批");
        return workOrderApprovalMapper.insertWorkOrderApproval(approval);
    }

    @Override
    public int approveDeletion(Long approvalId, Long approverId) {
        WorkOrderApproval approval = workOrderApprovalMapper.selectWorkOrderApprovalById(approvalId);
        approval.setStatus("已通过");
        approval.setApproverId(approverId);
        approval.setApprovalTime(new Date());
        int result = workOrderApprovalMapper.updateWorkOrderApproval(approval);
        if (result > 0) {
            workOrderMapper.deleteWorkOrderById(approval.getWorkOrderId());
        }
        return result;
    }

    @Override
    public int rejectDeletion(Long approvalId, Long approverId) {
        WorkOrderApproval approval = workOrderApprovalMapper.selectWorkOrderApprovalById(approvalId);
        approval.setStatus("已拒绝");
        approval.setApproverId(approverId);
        approval.setApprovalTime(new Date());
        return workOrderApprovalMapper.updateWorkOrderApproval(approval);
    }

    @Override
    public List<WorkOrderApproval> getApprovalList(WorkOrderApproval approval) {
        return workOrderApprovalMapper.selectWorkOrderApprovalList(approval);
    }
}
