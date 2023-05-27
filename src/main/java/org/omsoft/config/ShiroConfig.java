package org.omsoft.config;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.omsoft.filter.CorsBasicAuthenticationFilter;
import org.omsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager());

        Map<String,String> filterChain = new HashMap<>();
        filterChain.put("/** ","anon");
        filterChain.put("/addUser","corsAuth,roles[admin]");
        filterChain.put("/uploadFile","corsAuth,roles[admin]");
        filterChain.put("/delete","corsAuth,roles[admin]");
        filterChain.put("/search","anon");
        Map<String, Filter> filters = new HashMap<>();
        filters.put("anon", new AnonymousFilter());
        filters.put("roles", new RolesAuthorizationFilter());
        filters.put("corsAuth",new CorsBasicAuthenticationFilter());
        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(){
        final DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        securityManager.setCacheManager(new MemoryConstrainedCacheManager());
        return securityManager;
    }

    /*
    //Commented as we want DB validation
    @Bean(name = "realm")
    @DependsOn("lifecycleBeanPostProcessor")
    public Realm realm(){
        PropertiesRealm realm = new PropertiesRealm();
        realm.setCachingEnabled(true);
        return realm;
    }
*/

    @Bean(name = "realm")
    @DependsOn({"lifecycleBeanPostProcessor","userService"})
    public DatabaseRealm realm(){
        DatabaseRealm realm = new DatabaseRealm(userService());
        return realm;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "secondService")
    UserService userService() {
        return new UserService();
    }
}
