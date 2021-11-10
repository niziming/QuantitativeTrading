package cn.zm.strategy.web.rest;

import cn.zm.common.base.ResResult;
import cn.zm.common.config.GlobalConfig;
import cn.zm.plus.base.BaseController;
import cn.zm.strategy.web.service.RemoteCallService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author 十渊
 * @since 2021-10-12
 */
@RequestMapping("/remote/call")
@RestController
@Api(tags = "远程调用服务")
@Slf4j
public class RemoteCallController extends BaseController {
    @Resource
    private RemoteCallService ribbonService;
    @Resource
    private GlobalConfig globalConfig;

    @GetMapping
    @ApiOperation("获取全局配置")
    public ResResult getConfig() {
        // TODO 查询
        return ResResult.succ(globalConfig);
    }

    @PostMapping("/postCall")
    @ApiOperation("POST调用")
    public ResResult postCall(String service, @RequestBody JSONObject params) {
        long start = System.currentTimeMillis();
        log.info("start remote call");
        Object o = ribbonService.postCall(service, params);
        if (o instanceof List) o = ((List<?>) o).stream().limit(10).collect(Collectors.toList());
        // TODO 查询
        log.info("end remote call {}", (System.currentTimeMillis() - start));
        return ResResult.succ(o);
    }

    @GetMapping("/services")
    @ApiOperation("获取所有服务")
    @ApiImplicitParams(
      @ApiImplicitParam(
        name = "serviceName",
        value = "服务列表",
        dataType = "String",
        paramType = "query",
        allowableValues = "services"
      )
    )
    public ResResult services() {
        String services = globalConfig.getConfig()
          .getJSONObject("services").values()
          .stream()
          .map(a -> a.toString())
          .collect(Collectors.joining(","));
        // TODO 查询
        return ResResult.succ(services);
    }
}
