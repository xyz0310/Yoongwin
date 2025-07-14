package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.SysLeaveApplication;

/**
 * 请假申请管理 服务层
 *
 * @author wyh
 */
public interface ISysLeaveApplicationService
{
    /**
     * 查询请假申请列表
     *
     * @param leaveApplication 请假申请信息
     * @return 请假申请信息集合
     */
    public List<SysLeaveApplication> selectLeaveApplicationList(SysLeaveApplication leaveApplication);

    /**
     * 根据请假申请ID查询信息
     *
     * @param leaveId 请假申请ID
     * @return 请假申请信息
     */
    public SysLeaveApplication selectLeaveApplicationById(Long leaveId);

    /**
     * 新增保存请假申请信息
     *
     * @param leaveApplication 请假申请信息
     * @return 结果
     */
    public int insertLeaveApplication(SysLeaveApplication leaveApplication);

    /**
     * 修改保存请假申请信息
     *
     * @param leaveApplication 请假申请信息
     * @return 结果
     */
    public int updateLeaveApplication(SysLeaveApplication leaveApplication);

    /**
     * 删除请假申请管理信息
     *
     * @param leaveId 请假申请ID
     * @return 结果
     */
    public int deleteLeaveApplicationById(Long leaveId);
}