import request from '@/utils/request'

// 查询ups电源列表
export function listUps(query) {
  return request({
    url: '/ups/ups/list',
    method: 'get',
    params: query
  })
}

// 查询ups电源详细
export function getUps(id) {
  return request({
    url: '/ups/ups/' + id,
    method: 'get'
  })
}

// 新增ups电源
export function addUps(data) {
  return request({
    url: '/ups/ups',
    method: 'post',
    data: data
  })
}

// 修改ups电源
export function updateUps(data) {
  return request({
    url: '/ups/ups',
    method: 'put',
    data: data
  })
}

// 删除ups电源
export function delUps(id) {
  return request({
    url: '/ups/ups/' + id,
    method: 'delete'
  })
}

// 获取产品图片 URL
export function getProductImageUrl(data) {
  return request({
    url: '/ups/ups/getProductImageUrl',
    method: 'get',
    params: data
  })
}

export function getInputVoltagePhaseList() {
  return request({
    url: '/ups/ups/inputVoltagePhaseList',
    method: 'get'
  });
}

export function getOutputVoltagePhaseList() {
  return request({
    url: '/ups/ups/outputVoltagePhaseList',
    method: 'get'
  });
}