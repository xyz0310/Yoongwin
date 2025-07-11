package com.ruoyi.order.service.impl;

import com.ruoyi.order.domain.WorkOrderDeleteApply;
import com.ruoyi.order.mapper.WorkOrderDeleteApplyMapper;
import com.ruoyi.order.service.IWorkOrderDeleteApplyService;
import com.ruoyi.order.service.IWorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkOrderDeleteApplyServiceImpl implements IWorkOrderDeleteApplyService {
    @Autowired
    private WorkOrderDeleteApplyMapper workOrderDeleteApplyMapper;
    @Autowired
    private IWorkOrderService workOrderService;

    @Override
    public int insertWorkOrderDeleteApply(WorkOrderDeleteApply apply) {
        apply.setApplyTime(new Date());
        apply.setApprovalStatus("pending");
        return workOrderDeleteApplyMapper.insertWorkOrderDeleteApply(apply);
    }

    @Override
    public List<WorkOrderDeleteApply> selectWorkOrderDeleteApplyList(WorkOrderDeleteApply apply) {
        return workOrderDeleteApplyMapper.selectWorkOrderDeleteApplyList(apply);
    }

    @Override
    public int approveWorkOrderDeleteApply(Long applyId, String approvalStatus, Long approverId) {
        WorkOrderDeleteApply apply = workOrderDeleteApplyMapper.selectWorkOrderDeleteApplyById(applyId);
        apply.setApproverId(approverId);
        apply.setApprovalTime(new Date());
        apply.setApprovalStatus(approvalStatus);

        int result = workOrderDeleteApplyMapper.updateWorkOrderDeleteApply(apply);

        if ("approved".equals(approvalStatus)) {
            // 审批通过，删除工单记录
            workOrderService.deleteWorkOrderById(apply.getWorkOrderId());
        }

        return result;
    }
}