package org.bytewen.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.impl.Base64Codec;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.bytewen.mapper.UserMapper;
import org.bytewen.po.Result;
import org.bytewen.po.User;
import org.bytewen.service.UserService;
import org.bytewen.utils.CodecUtils;
import org.bytewen.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
public class OpenidController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/login")
    public Result login(@RequestParam("code") String code, HttpServletResponse resp,HttpServletRequest req) throws IOException, ServletException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost request=new HttpPost("https://github.com/login/oauth/access_token");
        List<NameValuePair> list=new LinkedList<>();
        BasicNameValuePair param1=new BasicNameValuePair("code",code);
        BasicNameValuePair param2=new BasicNameValuePair("client_id","7cf1324f440fe33bb738");
        BasicNameValuePair param3=new BasicNameValuePair("client_secret","1d9bb41d5e548c1d08b4259f04212bae748aada5");
        list.add(param1);
        list.add(param2);
        list.add(param3);
        UrlEncodedFormEntity encodedFormEntity=new UrlEncodedFormEntity(list,"utf-8");
        request.setEntity(encodedFormEntity);
        request.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
        // 传输的类型
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity, "utf-8");
        String substring = s.substring(0, s.indexOf('&'));

        Result result = getGithubUserInfo(substring);
        String s1 = objectMapper.writeValueAsString(s);
        Cookie cookie =new Cookie("userinfo", Base64Utils.encodeToString(s1.getBytes()));
//        cookie.setDomain("http://localhost:8000/");
//        cookie.setPath("/");
        resp.addCookie(cookie);
        resp.sendRedirect("http://localhost:8080/#/film");

        return result;
    }

    public Result getGithubUserInfo(String accessToken) throws IOException {
        String userUrl="https://api.github.com/user";
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet get=new HttpGet(userUrl+"?"+accessToken);
        get.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
        get.addHeader("Content-Type", "application/x-www-form-urlencoded");
        HttpResponse response = httpClient.execute(get);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity, "utf-8");
        Map<String,String> map = objectMapper.readValue(s, new TypeReference<Map<String, String>>() {
        });
        //TODO
        /**
         * 将用户名和id存入数据库
         */
        String id = map.get("id");
        User user1 = userService.checkBySalt(id);

        Map res=new HashMap();
        if(user1!=null){
            String token = jwtUtils.createJwt(user1.getId().toString(), user1.getUsername(), null);
            res.put("token",token);
            res.put("username",user1.getUsername());
            res.put("wife",user1.getWife());
            res.put("cityId",user1.getCityId());
            return new Result(true,"登录成功",map);
        }
        User user =new User();
        user.setSalt(map.get("id"));
        user.setUsername(map.get("login"));
        user.setPassword(CodecUtils.generateSalt());
        userService.register(user,null);
        String token=jwtUtils.createJwt(user.getId().toString(),user.getUsername(),null);
        res.put("token",token);
        res.put("username",user.getUsername());
        res.put("wife",user.getWife());
        res.put("cityId",user.getCityId());
        return new Result(true,"登录成功",res);
    }
}
