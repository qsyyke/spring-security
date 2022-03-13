package xyz.xcye.securitydemo1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import xyz.xcye.securitydemo1.entity.Users;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/8 23:51
 */
@Repository
public interface UserMapper extends BaseMapper<Users> {

}
