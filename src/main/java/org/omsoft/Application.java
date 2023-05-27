package org.omsoft;

import org.aspectj.lang.annotation.Before;
import org.omsoft.util.LoginUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Before destroy");
        LoginUtil.logout();
    }

}
