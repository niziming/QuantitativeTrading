package cn.zm.strategy.web.service.impl;

import cn.zm.common.config.GlobalConfig;
import cn.zm.strategy.web.service.RemoteCallService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RemoteCallServiceImpl implements RemoteCallService {
    @Resource
    RestTemplate restTemplate;
    @Resource
    private GlobalConfig globalConfig;

    @Override
    public Object postCall(String service, Object params) {
        String host = globalConfig.getConfig().getString("host");
        // HttpHeaders headers = new HttpHeaders();
        // headers.add("Content-Type", "application/json;charset=UTF-8");
        // HttpEntity<String> entity = new HttpEntity<>(JSONObject.toJSONString(params), headers);
        // HttpEntity httpEntity = new HttpEntity(params, headers);
        return restTemplate.postForObject(host+service, params, Object.class);
        // return restTemplate.postForEntity(host+service, entity, Object.class);
    }
}
