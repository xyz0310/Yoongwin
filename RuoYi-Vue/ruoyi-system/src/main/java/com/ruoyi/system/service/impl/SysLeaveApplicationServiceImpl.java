package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysLeaveApplicationMapper;
import com.ruoyi.system.service.ISysLeaveApplicationService;
import com.ruoyi.common.core.domain.entity.SysLeaveApplication;

/**
 * 请假申请管理 服务实现
 *
 * @author wyh
 */
@Service
public class SysLeaveApplicationServiceImpl implements ISysLeaveApplicationService
{
    @Autowired
    private SysLeaveApplicationMapper leaveApplicationMapper;

    /**
     * 查询请假申请列表
     *
     * @param leaveApplication 请假申请信息
     * @return 请假申请信息集合
     */
    @Override
    public List<SysLeaveApplication> selectLeaveApplicationList(SysLeaveApplication leaveApplication)
    {
        return leaveApplicationMapper.selectLeaveApplicationList(leaveApplication);
    }

    /**
     * 根据请假申请ID查询信息
     *
     * @param leaveId 请假申请ID
     * @return 请假申请信息
     */
    @Override
    public SysLeaveApplication selectLeaveApplicationById(Long leaveId)
    {
        return leaveApplicationMapper.selectLeaveApplicationById(leaveId);
    }

    /**
     * 新增保存请假申请信息
     *
     * @param leaveApplication 请假申请信息
     * @return 结果
     */
    @Override
    public int insertLeaveApplication(SysLeaveApplication leaveApplication)
    {
        return leaveApplicationMapper.insertLeaveApplication(leaveApplication);
    }

    /**
     * 修改保存请假申请信息
     *
     * @param leaveApplication 请假申请信息
     * @return 结果
     */
    @Override
    public int updateLeaveApplication(SysLeaveApplication leaveApplication)
    {
        return leaveApplicationMapper.updateLeaveApplication(leaveApplication);
    }

    /**
     * 删除请假申请管理信息
     *
     * @param leaveId 请假申请ID
     * @return 结果
     */
    @Override
    public int deleteLeaveApplicationById(Long leaveId)
    {
        return leaveApplicationMapper.deleteLeaveApplicationById(leaveId);
    }
}