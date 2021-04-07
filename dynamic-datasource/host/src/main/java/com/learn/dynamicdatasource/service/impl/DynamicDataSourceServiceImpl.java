package com.learn.dynamicdatasource.service.impl;

import com.learn.dynamicdatasource.handler.DataSourceContextHolder;
import com.learn.dynamicdatasource.service.DynamicDataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/4/7 9:49 上午
 */
@Slf4j
@Service("dynamicDataSourceServiceImpl")
public class DynamicDataSourceServiceImpl implements DynamicDataSourceService {

    private static final String PREFIX_LOG = "【自定义DB工具】";

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<Map<String, Object>> list(String sql) {
        log.info(PREFIX_LOG+"执行查询sql:"+sql);
        List<Map<String, Object>> list = new ArrayList<>();
        PreparedStatement pst = null;
        SqlSession session;
        try {
            session = getSqlSession();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("切换数据源失败");
        }
        ResultSet result;
        try {
            pst = session.getConnection().prepareStatement(sql);
            result = pst.executeQuery();
            //获得结果集结构信息,元数据
            ResultSetMetaData md = result.getMetaData();
            //获得列数
            int columnCount = md.getColumnCount();
            while (result.next()) {
                Map<String,Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), result.getObject(i));
                }
                list.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(pst!=null){
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            closeSqlSession(session);
        }
        return list;
    }

    /**
     * 获取sqlSession
     * @return
     */
    public SqlSession getSqlSession() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(DataSourceContextHolder.getDataSource());
        return SqlSessionUtils.getSqlSession(bean.getObject(),
                sqlSessionTemplate.getExecutorType(), sqlSessionTemplate.getPersistenceExceptionTranslator());
    }

    /**
     * 关闭sqlSession
     * @param session
     */
    public void closeSqlSession(SqlSession session){
        SqlSessionUtils.closeSqlSession(session, sqlSessionTemplate.getSqlSessionFactory());
    }
}
