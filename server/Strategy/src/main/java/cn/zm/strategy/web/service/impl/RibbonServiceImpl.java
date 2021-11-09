package cn.zm.strategy.web.service.impl;

import cn.zm.common.config.GlobalConfig;
import cn.zm.strategy.web.service.RibbonService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class RibbonServiceImpl implements RibbonService {
    @Resource
    RestTemplate restTemplate;

    @Resource
    private GlobalConfig globalConfig;


    @Override
    public String stockZhANew() {
        String host = globalConfig.getConfig().getString("host");
        JSONObject services = globalConfig.getConfig().getJSONObject("services");

        return restTemplate.getForObject(host+services.getString("stock_zh_a_new"), String.class);

    }
}
