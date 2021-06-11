package com.test.user.mapper;

import com.test.thrift.user.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    @Insert("insert into pe_user(username,password,real_name,mobile,email)"+
    "values(#{u.username},#{u.password},#{u.realName},#{u.mobile},#{u.email})")
    void registerUser(@Param("u") UserInfo userInfo);

    @Select("select username,password,real_name as realName,mobile,email from pe_user where id = #{id}")
    UserInfo getUserById(@Param("id") int id);

    @Select("select username,password,real_name as realName,mobile,email from pe_user where username = #{username}")
    UserInfo getUserByName(@Param("username") String username);
}
