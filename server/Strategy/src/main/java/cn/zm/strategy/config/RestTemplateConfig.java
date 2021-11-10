package cn.zm.strategy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Configuration
public class RestTemplateConfig {
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
  public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
    RestTemplate restTemplate = new RestTemplate(factory);
    // 支持中文编码
    restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    return restTemplate;
  }

  @Bean
  public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setReadTimeout(50000);//单位为ms
    factory.setConnectTimeout(50000);//单位为ms
    return factory;
  }

}
