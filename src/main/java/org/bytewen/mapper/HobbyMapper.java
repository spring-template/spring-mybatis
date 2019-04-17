package org.bytewen.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bytewen.po.Hobby;

import java.util.List;
import java.util.Map;

@Mapper
public interface HobbyMapper {
    public void add(Hobby hobby);
    public void delete(Long id);
    public void update(Hobby hobby);
    public Hobby get(Long id);
    public List<Hobby> getList(Map map);
    public List<Hobby>getListByPage(Map map);
}
