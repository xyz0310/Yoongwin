package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.common.core.domain.entity.SysLeaveApplication;

/**
 * 请假申请管理 数据层
 *
 * @author wyh
 */
public interface SysLeaveApplicationMapper
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
     * 新增请假申请信息
     *
     * @param leaveApplication 请假申请信息
     * @return 结果
     */
    public int insertLeaveApplication(SysLeaveApplication leaveApplication);

    /**
     * 修改请假申请信息
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