package fun.codefarmer.shiro1.config;

import fun.codefarmer.shiro1.realm.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ @ClassName ShiroConfig
 * @ Descriotion TODO
 * @ Author admin
 * @ Date 2020/2/15 16:02
 **/
@Configuration
public class ShiroConfig {
    @Bean
    MyRealm myRealm() {
        return  new MyRealm();
    }

    @Bean
    SecurityManager securityManager() {
        DefaultWebSecurityManager manage = new DefaultWebSecurityManager();
        manage.setRealm(myRealm());
        return manage;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        //设置登录地址
        bean.setLoginUrl("/login");
        // 登录成功地址
        bean.setSuccessUrl("");

        Map<String, String> map = new HashMap<>();
        //                 匿名访问
        map.put("/doLogin","anon");
                    //认证之后访问
        map.put("/**","authc");
        //设置拦截规则
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }
}
