package org.bytewen.service;

import org.bytewen.mapper.FilmMapper;
import org.bytewen.po.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FilmService {
    @Autowired
    private FilmMapper filmMapper;

    public List<Film> listByPage(int currentPage,int pageSize,Map map){
        Film film=new Film();
        film.setFilmName(map.get("search")==null?null:map.get("search").toString());
        map.put("start",(currentPage-1)*pageSize);
        map.put("size",pageSize);
        map.put("film",film);
        List<Film> list = this.filmMapper.getListByPage(map);
        return list;
    }
    public int count(){
        int count = this.filmMapper.count();
        return count;
    }
    public void delete(long id){
        this.filmMapper.delete(id);
    }
    public void update(Film film){
        this.filmMapper.update(film);
    }
    public void add(Film film){
        this.filmMapper.add(film);
    }

}
