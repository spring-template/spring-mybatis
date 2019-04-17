package org.bytewen.controller;

import org.apache.ibatis.annotations.Delete;
import org.bytewen.po.Film;
import org.bytewen.po.Result;
import org.bytewen.po.User;
import org.bytewen.service.FilmService;
import org.mockito.CheckReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("film")
public class FilmController {

    @Autowired
    private FilmService filmService;
    @CrossOrigin
    @PostMapping("list/{currentPage}/{pageSize}")
    public Result listByPage(
            @PathVariable(value = "currentPage",required = false) int currentPage,
            @PathVariable(value = "pageSize",required = false) int pageSize,
            @RequestBody HashMap map
            ){
        System.out.println(map);
        List<Film> films = filmService.listByPage(currentPage, pageSize, map);
        int count = filmService.count();
        Map res=new HashMap<>();
        res.put("films",films);
        res.put("total",count);
        return new Result(true,"查询成功",res);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") long id){
        this.filmService.delete(id);
        return new Result(true,"删除成功");
    }
    @PutMapping()
    public Result update(@RequestBody Film film){
        this.filmService.update(film);
        return new Result(true,"更新成功");
    }
    @PostMapping
    public Result add(@RequestBody Film film){
        this.filmService.add(film);
        return new Result(true,"新增成功");
    }
}
