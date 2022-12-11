package org.omsoft;

import org.aspectj.lang.annotation.Before;
import org.omsoft.util.LoginUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
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
