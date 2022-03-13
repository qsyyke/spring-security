package xyz.xcye.securitytest.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.xcye.securitytest.entity.User;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 11:12
 */
@Mapper
public interface UserMapper {
    User getUserByUsername(@Param("username") String username);
    int insertUser(@Param("user") User user);
}
