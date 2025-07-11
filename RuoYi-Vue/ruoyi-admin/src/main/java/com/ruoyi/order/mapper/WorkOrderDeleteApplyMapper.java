package com.ruoyi.order.mapper;

import com.ruoyi.order.domain.WorkOrderDeleteApply;

import java.util.List;

public interface WorkOrderDeleteApplyMapper {
    /**
     * 插入工单删除申请
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
     * 根据ID查询工单删除申请
     * @param applyId 申请ID
     * @return 申请信息
     */
    WorkOrderDeleteApply selectWorkOrderDeleteApplyById(Long applyId);

    /**
     * 更新工单删除申请信息
     * @param apply 申请信息
     * @return 结果
     */
    int updateWorkOrderDeleteApply(WorkOrderDeleteApply apply);
}