package com.ruoyi.system.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.system.service.HolidayService;
import com.ruoyi.system.service.ISysDeptService;
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
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private HolidayService holidayService;


    /**
     * 查询请假申请列表
     *
     * @param leaveApplication 请假申请信息
     * @return 请假申请信息集合
     */
    @Override
    public List<SysLeaveApplication> selectLeaveApplicationList(SysLeaveApplication leaveApplication) {
        List<SysLeaveApplication> list = leaveApplicationMapper.selectLeaveApplicationList(leaveApplication);

        // 批量查询部门，避免重复查询
        List<SysDept> deptList = deptService.selectDeptList(new SysDept());
        Map<Long, String> deptMap = deptList.stream()
                .collect(Collectors.toMap(SysDept::getDeptId, SysDept::getDeptName));

        for (SysLeaveApplication item : list) {
            if (item.getDeptId() != null) {
                item.setDeptName(deptMap.get(item.getDeptId()));
            }
        }

        return list;
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
    public int insertLeaveApplication(SysLeaveApplication leaveApplication) {
        // 调用跨区间接口，支持跨年
        LocalDate start = leaveApplication.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = leaveApplication.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Set<LocalDate> holidays = holidayService.getHolidaySetBetween(start, end);
        int leaveDays = calculateLeaveDays(leaveApplication.getStartTime(), leaveApplication.getEndTime(), holidays);
        leaveApplication.setLeaveDays(leaveDays);
        return leaveApplicationMapper.insertLeaveApplication(leaveApplication);
    }

    /**
     * 修改保存请假申请信息
     *
     * @param leaveApplication 请假申请信息
     * @return 结果
     */
    @Override
    public int updateLeaveApplication(SysLeaveApplication leaveApplication) {
        LocalDate start = leaveApplication.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = leaveApplication.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Set<LocalDate> holidays = holidayService.getHolidaySetBetween(start, end);
        int leaveDays = calculateLeaveDays(leaveApplication.getStartTime(), leaveApplication.getEndTime(), holidays);
        leaveApplication.setLeaveDays(leaveDays);
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

    private int calculateLeaveDays(Date start, Date end, Set<LocalDate> holidays) {
        LocalDate startDate = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int days = 0;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            boolean isWeekend = date.getDayOfWeek().getValue() >= 6;
            if (!isWeekend && !holidays.contains(date)) {
                days++;
            }
        }


        return days;
    }


}