package cn.zm.strategy.web.service.impl;


import cn.zm.common.config.GlobalConfig;
import cn.zm.strategy.web.service.RemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@Service
public class RemoteServiceImpl implements RemoteService {
  @Resource
  RestTemplate restTemplate;
  @Resource
  private GlobalConfig globalConfig;

  @Override
  public <T> T postCall(String service, Object params, Class<T> type) {
    String host = globalConfig.getConfig().getString("host");
    // HttpHeaders headers = new HttpHeaders();
    // headers.add("Content-Type", "application/json;charset=UTF-8");
    // HttpEntity<String> entity = new HttpEntity<>(JSONObject.toJSONString(params), headers);
    // HttpEntity httpEntity = new HttpEntity(params, headers);
    return restTemplate.postForObject(host + service, params, type);
    // return restTemplate.postForEntity(host+service, entity, Object.class);
  }
}
