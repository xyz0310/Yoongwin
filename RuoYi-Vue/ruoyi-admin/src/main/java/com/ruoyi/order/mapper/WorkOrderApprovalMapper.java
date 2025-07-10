package com.ruoyi.order.mapper;


import com.ruoyi.order.domain.WorkOrderApproval;

import java.util.List;

public interface WorkOrderApprovalMapper {
    int insertWorkOrderApproval(WorkOrderApproval approval);
    WorkOrderApproval selectWorkOrderApprovalById(Long id);
    int updateWorkOrderApproval(WorkOrderApproval approval);
    List<WorkOrderApproval> selectWorkOrderApprovalList(WorkOrderApproval approval);
}
