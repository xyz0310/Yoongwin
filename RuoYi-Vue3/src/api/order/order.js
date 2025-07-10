import request from '@/utils/request'

// 查询工单记录列表
export function listOrder(query) {
  return request({
    url: '/order/order/list',
    method: 'get',
    params: query
  })
}

// 查询工单记录详细
export function getOrder(id) {
  return request({
    url: '/order/order/' + id,
    method: 'get'
  })
}

// 新增工单记录
export function addOrder(data) {
  return request({
    url: '/order/order',
    method: 'post',
    data: data
  })
}

// 修改工单记录
export function updateOrder(data) {
  return request({
    url: '/order/order',
    method: 'put',
    data: data
  })
}

// 删除工单记录
export function delOrder(id) {
  return request({
    url: '/order/order/' + id,
    method: 'delete'
  })
}

// 获取技术员列表
export function getTechnicians() {
  return request({
    url: '/order/order/technicians',
    method: 'get'
  })
}

// 获取业务员列表
export function getSalesmen() {
  return request({
    url: '/order/order/salesmen',
    method: 'get'
  })
}