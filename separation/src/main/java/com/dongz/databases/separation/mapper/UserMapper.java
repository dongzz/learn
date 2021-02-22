package com.dongz.databases.separation.mapper;

import com.dongz.databases.separation.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/2/19 10:30 下午
 */
@Mapper
public interface UserMapper {

    User getByName(@Param("name") String name);

    int insert(@Param("id") String id, @Param("name") String name);
}
