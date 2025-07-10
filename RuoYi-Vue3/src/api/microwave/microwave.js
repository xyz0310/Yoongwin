import request from '@/utils/request'

// 查询无线微波器械列表
export function listMicrowave(query) {
  return request({
    url: '/microwave/microwave/list',
    method: 'get',
    params: query
  })
}

// 查询无线微波器械详细
export function getMicrowave(id) {
  return request({
    url: '/microwave/microwave/' + id,
    method: 'get'
  })
}

// 新增无线微波器械
export function addMicrowave(data) {
  return request({
    url: '/microwave/microwave',
    method: 'post',
    data: data
  })
}

// 修改无线微波器械
export function updateMicrowave(data) {
  return request({
    url: '/microwave/microwave',
    method: 'put',
    data: data
  })
}

// 删除无线微波器械
export function delMicrowave(id) {
  return request({
    url: '/microwave/microwave/' + id,
    method: 'delete'
  })
}
