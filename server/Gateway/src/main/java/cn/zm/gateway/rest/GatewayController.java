package cn.zm.gateway.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class GatewayController {
  @GetMapping("/fallback")
  public String fallback() {
    return "我是hystrix熔断后界面";
  }

}
