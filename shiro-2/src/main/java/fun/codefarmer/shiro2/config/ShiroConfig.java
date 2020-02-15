package fun.codefarmer.shiro2.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ @ClassName ShiroConfig
 * @ Descriotion TODO
 * @ Author admin
 * @ Date 2020/2/15 17:04
 **/
@Configuration
public class ShiroConfig {
    @Bean
    Realm realm() {
        TextConfigurationRealm realm = new TextConfigurationRealm();
        //内存中设置两个用户
        realm.setUserDefinitions("codefarmer=123,user \n admin=123,admin");
        //给角色设置权限
        realm.setRoleDefinitions("admin=read,write \n user=read");
        return realm;
    }

    //ShiroFilterFactoryBean 中的拦截规则 需要配置
    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/doLogin","anon");
        definition.addPathDefinition("/**","authc");
        return definition;
    }
}
