import request from '@/utils/request';

// 获取工单删除申请列表
export function getWorkOrderDeleteApplyList() {
    return request({
        url: '/order/deleteApply/list',
        method: 'get'
    });
}

// 审批工单删除申请
export function approveWorkOrderDeleteApply(applyId, approvalStatus) {
    return request({
        url: '/order/deleteApply/approve',
        method: 'post',
        params: {
            applyId,
            approvalStatus
        }
    });
}