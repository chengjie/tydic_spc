package com.tydic.spc.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.tydic.spc.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: chengjie
 * Date: 13-3-25
 * Time: 下午5:29
 */
@Repository
public interface UserMapper {
    @Select("SELECT USER_ID userid, REALNAME username FROM TB_ASS_MANAGE_USER WHERE USER_ID = #{id}")
    User findUser(@Param(value = "id") String userid );
}
