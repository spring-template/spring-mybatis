package org.bytewen.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bytewen.po.User;

import java.util.List;

@Mapper
public interface UserMapper {
    public void add(User user);
    public void delete(@Param("id") Long id);
    public void update(User user);
    public User get(@Param("id") Long id);
    public User getByUsername(@Param("username")String username);
    public List<User> getList(User user);
    public List<User> listByPage(@Param("start") int currentPage,@Param("size") int pageSize,@Param("username") String username,@Param("wife") String wife);
    public User getBySalt(@Param("salt") String salt);
}
