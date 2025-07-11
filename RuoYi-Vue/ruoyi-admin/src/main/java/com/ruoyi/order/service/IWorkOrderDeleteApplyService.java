package com.ruoyi.order.service;

import com.ruoyi.order.domain.WorkOrderDeleteApply;

import java.util.List;

public interface IWorkOrderDeleteApplyService {
    /**
     * 创建工单删除申请
     * @param apply 申请信息
     * @return 结果
     */
    int insertWorkOrderDeleteApply(WorkOrderDeleteApply apply);

    /**
     * 查询工单删除申请列表
     * @param apply 申请信息
     * @return 申请列表
     */
    List<WorkOrderDeleteApply> selectWorkOrderDeleteApplyList(WorkOrderDeleteApply apply);

    /**
     * 审批工单删除申请
     * @param applyId 申请ID
     * @param approvalStatus 审批状态
     * @param approverId 审批人ID
     * @return 结果
     */
    int approveWorkOrderDeleteApply(Long applyId, String approvalStatus, Long approverId);
}