package fun.codefarmer.shiro2.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ @ClassName HelloController
 * @ Descriotion TODO
 * @ Author admin
 * @ Date 2020/2/15 17:26
 **/
@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello shiro";
    }
    @GetMapping("/login")
    @ResponseBody
    public String login() {
        return "please login";
    }
    @PostMapping("/doLogin")
    @ResponseBody
    public void doLogin(String username,String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
            System.out.println("success");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("fail>>"+e.getMessage());
        }

    }

    /**
     * 返回html页面
     * properties 需配置后缀 spring.mvc.view.suffix=.html
     * 否则无法返回前端页面，
     * @RestController注解相当于@ResponseBody和@Controller合在一起的作用。在使用@RestController注解Controller时，
     * Controller中的方法无法返回jsp页面，或者html，配置的视图解析器 InternalResourceViewResolver不起作用，
     * 返回的内容就是Return 里的内容。
     * 包括在Mapping注解使用的同时使用@ResponseBody时也会出现同样的问题。
     * 解决办法：①去除@ResponseBody或将含有Rest的注解换成对应的原始注解；
     *②不通过String返回，通过ModelAndView对象返回，上述例子可将return语句换成下面的句子：
     * return new ModelAndView("index");
     * @GetMapping("/html") 路径一定不要和返回的页面名称完全相同，这样会报500的错误！！！！
     * @return
     */
    @GetMapping("/html")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
