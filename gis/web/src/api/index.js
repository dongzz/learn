import http from '../utils/http'

export const getGeometry = () => {
  return http.get('/api/map/geometry?category=cntypts&start=-5000&end=-221')
}
