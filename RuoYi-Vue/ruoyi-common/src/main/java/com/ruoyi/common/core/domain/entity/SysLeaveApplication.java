package com.ruoyi.common.core.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 请假申请表 sys_leave_application
 *
 * @author wyh
 */
public class SysLeaveApplication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 请假申请ID */
    private Long leaveId;

    /** 申请人用户ID */
    private Long userId;

    /** 申请人所在部门ID */
    private Long deptId;

    /** 请假开始时间 */
    private Date startTime;

    /** 请假结束时间 */
    private Date endTime;

    /** 请假原因 */
    private String reason;

    /** 申请状态（0待审批 1已同意 2已拒绝） */
    private String status;

    /** 审批意见 */
    private String approvalOpinion;

    public Long getLeaveId()
    {
        return leaveId;
    }

    public void setLeaveId(Long leaveId)
    {
        this.leaveId = leaveId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getApprovalOpinion()
    {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion)
    {
        this.approvalOpinion = approvalOpinion;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("leaveId", getLeaveId())
                .append("userId", getUserId())
                .append("deptId", getDeptId())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("reason", getReason())
                .append("status", getStatus())
                .append("approvalOpinion", getApprovalOpinion())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}