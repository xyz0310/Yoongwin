package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private TokenService tokenService;

    /**
     * 获取请假申请列表
     */
    @GetMapping("/list")
    public AjaxResult list(SysLeaveApplication leaveApplication)
    {
        // 获取当前用户
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser currentUser = loginUser.getUser();

        boolean isAdmin = "admin".equals(currentUser.getUserName());
        // 是否为具有角色 ID = 101 的角色
        boolean isSpecialRole = currentUser.getRoles().stream()
                .anyMatch(role -> role.getRoleId() != null && role.getRoleId() == 101L);
        boolean isLeader = currentUser.getRoles().stream()
                .map(SysRole::getRoleKey)
                .anyMatch(roleKey -> roleKey != null && roleKey.contains("leader"));
        // 非管理员
        if (!isAdmin && !isSpecialRole) {
            if (isLeader) {
                // 领导只看本部门
                leaveApplication.setDeptId(currentUser.getDeptId());
            } else {
                // 普通员工只能看自己
                leaveApplication.setApplicant(currentUser.getNickName());
            }
        }

        List<SysLeaveApplication> list = leaveApplicationService.selectLeaveApplicationList(leaveApplication);
        return success(list);
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