import request from '@/utils/request'

/*
 *客户列表
 */
export function getGeometry(data) {
  return request({
    url: '/api/map/geometry',
    method: 'get',
    params: data
  })
}
