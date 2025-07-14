package com.ruoyi.web.controller.system;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysLeaveApplication;
import com.ruoyi.system.service.ISysLeaveApplicationService;

/**
 * 请假申请信息
 *
 * @author wyh
 */
@RestController
@RequestMapping("/leave/leave")
public class SysLeaveApplicationController extends BaseController
{
    @Autowired
    private ISysLeaveApplicationService leaveApplicationService;

    /**
     * 获取请假申请列表
     */
    @GetMapping("/list")
    public AjaxResult list(SysLeaveApplication leaveApplication)
    {
        List<SysLeaveApplication> leaveApplications = leaveApplicationService.selectLeaveApplicationList(leaveApplication);
        return success(leaveApplications);
    }

    /**
     * 根据请假申请编号获取详细信息
     */
    @GetMapping(value = "/{leaveId}")
    public AjaxResult getInfo(@PathVariable Long leaveId)
    {
        return success(leaveApplicationService.selectLeaveApplicationById(leaveId));
    }

    /**
     * 新增请假申请
     */
    @PostMapping
    public AjaxResult add(@RequestBody SysLeaveApplication leaveApplication)
    {
        return toAjax(leaveApplicationService.insertLeaveApplication(leaveApplication));
    }

    /**
     * 修改请假申请
     */
    @PutMapping
    public AjaxResult edit(@RequestBody SysLeaveApplication leaveApplication)
    {
        return toAjax(leaveApplicationService.updateLeaveApplication(leaveApplication));
    }

    /**
     * 删除请假申请
     */
    @DeleteMapping("/{leaveId}")
    public AjaxResult remove(@PathVariable Long leaveId)
    {
        return toAjax(leaveApplicationService.deleteLeaveApplicationById(leaveId));
    }
}