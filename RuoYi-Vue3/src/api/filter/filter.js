import request from '@/utils/request'

// 查询滤波器列表
export function listFilter(query) {
  return request({
    url: '/filter/filter/list',
    method: 'get',
    params: query
  })
}

// 查询滤波器详细
export function getFilter(id) {
  return request({
    url: '/filter/filter/' + id,
    method: 'get'
  })
}

// 新增滤波器
export function addFilter(data) {
  return request({
    url: '/filter/filter',
    method: 'post',
    data: data
  })
}

// 修改滤波器
export function updateFilter(data) {
  return request({
    url: '/filter/filter',
    method: 'put',
    data: data
  })
}

// 删除滤波器
export function delFilter(id) {
  return request({
    url: '/filter/filter/' + id,
    method: 'delete'
  })
}
