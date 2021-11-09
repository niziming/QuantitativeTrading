package cn.zm.netflix.ribbon.web.service.impl;

import cn.zm.netflix.ribbon.web.service.RibbonService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class RibbonServiceImpl implements RibbonService {
    @Resource
    RestTemplate restTemplate;

    @Override
    public String consume() {
        return restTemplate.getForObject("http://SERVICE-APP/account/ribbon/service", String.class);
    }
}
