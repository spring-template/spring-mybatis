package org.bytewen.mapper;

import org.bytewen.ApplicationStarter;
import org.bytewen.po.Hobby;
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
public class HobbyTest {

    @Autowired
    private HobbyMapper hobbyMapper;
    @Test
    public void add(){
        Hobby hobby=new Hobby();
        hobby.setName("lol");
        hobby.setScale("bg");
        hobby.setSource("usa");
        hobbyMapper.add(hobby);
        System.out.println(hobby.getId());
    }
    @Test
    public void delete(){
        this.hobbyMapper.delete(8l);
    }
    @Test
    public void update(){
        Hobby hobby=new Hobby();
        hobby.setId(8l);
        hobby.setName("英雄联盟");
        this.hobbyMapper.update(hobby);
    }
    @Test
    public void get(){
        Hobby hobby = this.hobbyMapper.get(8l);
        System.out.println(hobby.getName());
    }
    @Test
    public void list(){
        Map map=new HashMap<>();
        List<Hobby> list = this.hobbyMapper.getList(null);
        System.out.println(list.size());
    }
    @Test
    public void listByPage(){
        Map map=new HashMap<>();
        map.put("start",2);
        map.put("size",3);
        List<Hobby> list = this.hobbyMapper.getListByPage(map);
        System.out.println(list.size());
    }
}
