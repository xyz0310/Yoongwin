import request from '@/utils/request'

// 查询请假申请列表
export function listLeaveApplication(query) {
  return request({
    url: '/system/leave/list',
    method: 'get',
    params: query
  })
}

// 查询请假申请详细
export function getLeaveApplication(leaveId) {
  return request({
    url: '/system/leave/' + leaveId,
    method: 'get'
  })
}

// 新增请假申请
export function addLeaveApplication(data) {
  return request({
    url: '/system/leave',
    method: 'post',
    data: data
  })
}

// 修改请假申请
export function updateLeaveApplication(data) {
  return request({
    url: '/system/leave',
    method: 'put',
    data: data
  })
}

// 删除请假申请
export function deleteLeaveApplication(leaveId) {
  return request({
    url: '/system/leave/' + leaveId,
    method: 'delete'
  })
}

// 导出请假申请
export function exportLeaveApplication(query) {
  return request({
    url: '/system/leave/export',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}    