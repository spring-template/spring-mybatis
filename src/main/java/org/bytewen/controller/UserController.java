package org.bytewen.controller;

import org.bytewen.po.Result;
import org.bytewen.po.User;
import org.bytewen.service.UserService;
import org.bytewen.utils.CodecUtils;
import org.bytewen.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping("login")
//  @CrossOrigin //也可以直接使用注解的方式解决跨域，不需要过滤器
    public Result login(@RequestParam("username") String username,@RequestParam("password") String password){
        try{
            User user2= userService.getByUsername(username);
            if(CodecUtils.md5Hex(password,user2.getSalt()).equals(user2.getPassword())){
                Map<String,String > map=new HashMap<String,String>();
                String token= jwtUtils.createJwt(user2.getId().toString(),user2.getUsername(),null);
                map.put("token",token);
                map.put("username",username);
                map.put("wife",user2.getWife());
                map.put("cityId",user2.getCityId());
                return new Result(true,"登录成功",map);
            }
        }catch (Exception e){
            throw new RuntimeException("用户名或密码错误");
        }
        return new Result(false,"用户名或密码错误");
    }
    @PostMapping("register")
    public Result register(@RequestBody User user,@RequestParam("hobbyId") Long hobbyId){
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        user.setPassword(CodecUtils.md5Hex(user.getPassword(),salt));
        userService.register(user,hobbyId);
        return new Result(true,"注册成功");
    }
}
