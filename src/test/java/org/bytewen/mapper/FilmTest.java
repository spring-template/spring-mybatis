package org.bytewen.mapper;

import org.bytewen.ApplicationStarter;
import org.bytewen.po.Film;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class FilmTest {
    @Autowired
    private FilmMapper filmMapper;

    @Test
    public void add(){
        Film film=new Film();
        film.setFilmName("流浪地球");
        film.setActor("吴京");
        film.setDirector("郭帆");
        film.setActress("无");
        film.setDate(new Date(2018,12,12));
        filmMapper.add(film);
    }
    @Test
    public void delete(){
        this.filmMapper.delete(24l);
    }
    @Test
    public void update() throws ParseException {
        Film film=new Film();
        film.setId(24l);
        film.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-20"));
        this.filmMapper.update(film);
    }
    @Test
    public void list(){
        Film film=new Film();
        String firstKey="date";
        String order="asc";
        Map<String,Object> map=new HashMap<>();
        map.put("film",film);
        map.put("firstKey",firstKey);
        map.put("order",order);
        List<Film> list = this.filmMapper.getList(map);
        System.out.println(list.size());
        for (Film film1 : list) {
            System.out.println(film1.getFilmName()+"..."+film1.getDate());
        }
    }
    @Test
    public void listByPage(){
//        map.put("firstKey","date");
//        map.put("order","asc");
        Map map=new HashMap<>();
        Film films=new Film();
//        films.setFilmName("霸王别姬");
        map.put("start",0);
        map.put("size",5);
        map.put("order","desc");
        map.put("orderColumn","filmName");
        map.put("film",films);
        List<Film> list = this.filmMapper.getListByPage(map);
        for (Film film : list) {
            System.out.println(film.getFilmName()+"...."+film.getDate());
        }
    }
}
