import request from '@/utils/request'

// 查询请假申请列表
export function listLeaveApplication(query) {
  return request({
    url: '/leave/leave/list',
    method: 'get',
    params: query
  })
}

// 查询请假申请详细
export function getLeaveApplication(leaveId) {
  return request({
    url: '/leave/leave/' + leaveId,
    method: 'get'
  })
}

// 新增请假申请
export function addLeaveApplication(data) {
  return request({
    url: '/leave/leave',
    method: 'post',
    data: data
  })
}

// 修改请假申请
export function updateLeaveApplication(data) {
  return request({
    url: '/leave/leave',
    method: 'put',
    data: data
  })
}

// 删除请假申请
export function deleteLeaveApplication(leaveId) {
  return request({
    url: '/leave/leave/' + leaveId,
    method: 'delete'
  })
}

// 导出请假记录
export function exportLeaveApplication(query) {
  return request({
    url: '/leave/leave/export/list',
    method: 'get',
    params: query,
    responseType: 'blob'
  }).then(response => {
    const blob = new Blob([response]);
    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    link.download = '请假记录.xlsx';
    link.click();
  });
} 