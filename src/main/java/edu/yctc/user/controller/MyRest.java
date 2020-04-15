package edu.yctc.user.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author luna@win10
 * @date 2020/3/21 16:07
 */
@RestController
@RequestMapping("thymeleaf")
public class MyRest {

    @GetMapping("getUser")
    public String getUrl(String userId) {
        System.out.println("链接访问" + userId);
        return userId;
    }

    @GetMapping("getIdAndName")
    public String getUrl(String userId, String name) {
        System.out.println("链接访问" + userId + name);
        return userId + name;
    }

    @RequestMapping(value = "/web/name/{name}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String rest(@PathVariable String name) {
        System.out.println(name);
        return name;
    }

    @RequestMapping(value = "/xx/{name}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    private String query(@PathVariable String name) {
        String result = "查询一个名为" + name + "的结果";
        return result;
    }


}
