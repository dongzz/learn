import http from '@/utils/http'

export const getDatasource = (data) => {
    return http.get("/datasource/list", data)
}