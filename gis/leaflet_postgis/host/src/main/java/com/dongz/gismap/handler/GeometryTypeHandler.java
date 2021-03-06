package com.dongz.gismap.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;
import org.postgresql.util.PGobject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/5/7 2:01 下午
 */
@MappedTypes(Geometry.class)
@MappedJdbcTypes(JdbcType.OTHER)
public class GeometryTypeHandler extends BaseTypeHandler<Geometry> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Geometry geometry, JdbcType jdbcType) throws SQLException {
        PGobject pGobject=new PGobject();
        pGobject.setValue(geometry.toString());
        pGobject.setType("geometry");
        preparedStatement.setObject(i,pGobject);
    }

    @Override
    public Geometry getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        PGobject pGgeometry= (PGobject) resultSet.getObject(columnName);
        if(pGgeometry==null){
            return null;
        }else{
            WKBReader wkbReader=new WKBReader();
            try {
                return wkbReader.read(WKBReader.hexToBytes(pGgeometry.getValue()));
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public Geometry getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        PGobject pGgeometry= (PGobject) resultSet.getObject(columnIndex);
        if(pGgeometry==null){
            return null;
        }else{
            WKBReader wkbReader=new WKBReader();
            try {
                return wkbReader.read(WKBReader.hexToBytes(pGgeometry.getValue()));
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public Geometry getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        PGobject pGgeometry= (PGobject) callableStatement.getObject(i);
        if(pGgeometry==null){
            return null;
        }else{
            WKBReader wkbReader=new WKBReader();
            try {
                return wkbReader.read(WKBReader.hexToBytes(pGgeometry.getValue()));
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}