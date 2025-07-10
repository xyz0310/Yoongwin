import request from '@/utils/request'

/**
 * 工单删除审批相关API
 */
export default {
  /**
   * 添加删除审批请求
   * @param data
   * @returns {AxiosPromise}
   */
  addDeleteApproval(data) {
    return request({
      url: '/order/approval/addDeleteApproval',
      method: 'post',
      data: data
    })
  },

  /**
   * 审批删除请求
   * @param data
   * @returns {AxiosPromise}
   */
  approveDelete(data) {
    return request({
      url: '/order/approval/approveDelete',
      method: 'post',
      data: data
    })
  },

  /**
   * 获取删除审批列表
   * @param query
   * @returns {AxiosPromise}
   */
  getDeleteApprovalList(query) {
    return request({
      url: '/order/approval/list',
      method: 'get',
      params: query
    })
  }
}