import request from '@/utils/request'

// 查询无线天线列表
export function listAntenna(query) {
  return request({
    url: '/antenna/antenna/list',
    method: 'get',
    params: query
  })
}

// 查询无线天线详细
export function getAntenna(id) {
  return request({
    url: '/antenna/antenna/' + id,
    method: 'get'
  })
}

// 新增无线天线
export function addAntenna(data) {
  return request({
    url: '/antenna/antenna',
    method: 'post',
    data: data
  })
}

// 修改无线天线
export function updateAntenna(data) {
  return request({
    url: '/antenna/antenna',
    method: 'put',
    data: data
  })
}

// 删除无线天线
export function delAntenna(id) {
  return request({
    url: '/antenna/antenna/' + id,
    method: 'delete'
  })
}

// 获取所有天线种类
export function getAllAntennaTypes() {
  return request({
    url: '/antenna/antenna/allAntennaTypes',
    method: 'get'
  })
}

// 获取所有室内外类型
export function getAllIndoorOutdoorTypes() {
  return request({
    url: '/antenna/antenna/allIndoorOutdoorTypes',
    method: 'get'
  })
}

// 获取所有全定向类型
export function getAllOmniDirectionalTypes() {
  return request({
    url: '/antenna/antenna/allOmniDirectionalTypes',
    method: 'get'
  })
}

// 获取所有极化方式类型
export function getAllPolarizationTypes() {
  return request({
    url: '/antenna/antenna/allPolarizationTypes',
    method: 'get'
  })
}