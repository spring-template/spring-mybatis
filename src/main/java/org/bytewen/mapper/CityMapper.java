package org.bytewen.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bytewen.po.City;

import java.util.List;

@Mapper
public interface CityMapper {
    public void add(City city);
    public void delete(Long id);
    public void update(City city);
    public City get(Long id);
    public List<City> getList(City city);
    public List<City >getListByPage(int start,int size,String city,String flower,String province);
}
