package org.bytewen.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bytewen.po.Film;

import java.util.List;
import java.util.Map;

@Mapper
public interface FilmMapper {
    public void add(Film film);
    public void delete(Long id);
    public void update(Film film);
    public int count();
    public Film get(Long id);
    public List<Film> getList(Map map);
    public List<Film> getListByPage(Map map);
}
