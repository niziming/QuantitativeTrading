package cn.zm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Strategy {
    public static void main(String[] args) {
        SpringApplication.run(Strategy.class, args);
    }
    /** 功能描述: <br>
     * <向程序的ioc注入一个bean: restTemplate;
     * 并通过@LoadBalanced注解表明这个restRemplate
     * 开启负载均衡的功能。>
     *
     * @param
     *
     * @author 十渊
     * @date 2021/10/18 10:16
     * @return org.springframework.web.client.RestTemplate
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate () {
        return new RestTemplate();
    }
}
