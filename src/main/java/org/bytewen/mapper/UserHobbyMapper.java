package org.bytewen.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bytewen.po.UserHobby;

@Mapper
public interface UserHobbyMapper {
    public void add(UserHobby userHobby);

}
