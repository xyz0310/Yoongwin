package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.WorkOrder;

/**
 * 工单记录Mapper接口
 * 
 * @author wyh
 * @date 2025-06-26
 */
public interface WorkOrderMapper 
{
    /**
     * 查询工单记录
     * 
     * @param id 工单记录主键
     * @return 工单记录
     */
    public WorkOrder selectWorkOrderById(Long id);

    /**
     * 查询工单记录列表
     * 
     * @param workOrder 工单记录
     * @return 工单记录集合
     */
    public List<WorkOrder> selectWorkOrderList(WorkOrder workOrder);

    /**
     * 新增工单记录
     * 
     * @param workOrder 工单记录
     * @return 结果
     */
    public int insertWorkOrder(WorkOrder workOrder);

    /**
     * 修改工单记录
     * 
     * @param workOrder 工单记录
     * @return 结果
     */
    public int updateWorkOrder(WorkOrder workOrder);

    /**
     * 删除工单记录
     * 
     * @param id 工单记录主键
     * @return 结果
     */
    public int deleteWorkOrderById(Long id);

    /**
     * 批量删除工单记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkOrderByIds(Long[] ids);

    /**
     * 获取技术员列表
     */
    List<String> getTechnicians();

    /**
     * 获取业务员列表
     */
    List<String> getSalesmen();
}
