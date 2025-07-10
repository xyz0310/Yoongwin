import request from '@/utils/request'

// 查询光纤光缆列表
export function listOpticalFiber(query) {
  return request({
    url: '/opticalFiber/opticalFiber/list',
    method: 'get',
    params: query
  })
}

// 查询光纤光缆详细
export function getOpticalFiber(id) {
  return request({
    url: '/opticalFiber/opticalFiber/' + id,
    method: 'get'
  })
}

// 新增光纤光缆
export function addOpticalFiber(data) {
  return request({
    url: '/opticalFiber/opticalFiber',
    method: 'post',
    data: data
  })
}

// 修改光纤光缆
export function updateOpticalFiber(data) {
  return request({
    url: '/opticalFiber/opticalFiber',
    method: 'put',
    data: data
  })
}

// 删除光纤光缆
export function delOpticalFiber(id) {
  return request({
    url: '/opticalFiber/opticalFiber/' + id,
    method: 'delete'
  })
}
