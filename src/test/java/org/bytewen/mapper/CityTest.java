package org.bytewen.mapper;

import org.bytewen.ApplicationStarter;
import org.bytewen.po.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class CityTest {
    @Autowired
    private CityMapper cityMapper;

    /**
     * 增加城市
     */
    @Test
    public void add(){
        City city=new City();
        city.setId(8l);
        city.setCity("琼州");
        city.setFlower("君子兰");
        city.setProvince("海南");
        cityMapper.add(city);
    }
    /**
     * 删除城市
     */
    @Test
    public void delete(){
        cityMapper.delete(8l);
    }
    /**
     * 更新城市
     */
    @Test
    public void update(){
        City city=new City();
        city.setId(8l);
        city.setFlower("紫罗兰");
        cityMapper.update(city);
    }
    /**
     * 获得城市
     */
    @Test
    public void get(){
        City city = cityMapper.get(8l);
        System.out.println(city.getCity());
    }
    /**
     * 条件查询城市
     */
    @Test
    public void getList(){
        City city=new City();
        city.setFlower("花");
        List<City> list = cityMapper.getList(city);
        System.out.println(list.size());
    }
    /**
     * 条件分页查询城市
     */
    @Test
    public void getListByPage(){
        City city=new City();
        city.setFlower("花");
        List<City> cities = cityMapper.getListByPage(3, 3, null, "花", null);
        for (City c : cities) {
            System.out.println(c.getCity()+"..."+c.getFlower());
        }
    }
}
