package org.bytewen.service;

import org.bytewen.ApplicationStarter;
import org.bytewen.po.Film;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class FilmTest {

    @Autowired
    private FilmService filmService;
    @Test
    public void listByPage() {
        Map map=new HashMap<>();
        map.put("start",2);
        map.put("size",5);
        map.put("order","desc");
        map.put("orderColumn","date");
        map.put("film",new Film());
        List<Film> films = filmService.listByPage(3, 5,map);
        for (Film film : films) {
            System.out.println(film.getFilmName()+".."+film.getDate());
        }
    }
}
