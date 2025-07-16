package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
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

    /** 申请人姓名 */
    @Excel(name = "申请人")
    private String applicant;

    /** 请假申请ID */
    private Long leaveId;

    /** 申请人用户ID */
    private Long userId;

    /** 申请人所在部门ID */
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门")
    private String deptName;

    /** 请假开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 请假结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    @Excel(name = "请假天数")
    private Integer leaveDays;

    /** 请假原因 */
    private String reason;

    /** 申请状态（0待审批 1已同意 2已拒绝） */
    @Excel(name = "状态", readConverterExp = "0=待审批,1=同意,2=拒绝")
    private String status;

    /** 审批意见 */
    @Excel(name = "审批意见")
    private String approvalOpinion;



    public Integer getLeaveDays() {
        return leaveDays;
    }
    public void setLeaveDays(Integer leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("leaveId", getLeaveId())
                .append("userId", getUserId())
                .append("deptId", getDeptId())
                .append("deptName", getDeptName())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("leaveDays", getLeaveDays())
                .append("reason", getReason())
                .append("status", getStatus())
                .append("approvalOpinion", getApprovalOpinion())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("applicant", getApplicant())
                .toString();
    }
}