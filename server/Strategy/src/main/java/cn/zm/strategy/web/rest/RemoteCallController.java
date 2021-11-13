package cn.zm.strategy.web.rest;

import cn.zm.common.base.ResResult;
import cn.zm.common.config.GlobalConfig;
import cn.zm.plus.base.BaseController;
import cn.zm.strategy.web.service.RemoteService;
import cn.zm.strategy.web.entity.FundBasicInfo;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * 功能描述: <br>
 * <远程调用服务>
 *
 * @author 十渊
 * @date 2021/11/11 10:35
 * @return
 */
@RequestMapping("/remote/call")
@RestController
@Api(tags = "远程调用服务")
@Slf4j
public class RemoteCallController extends BaseController {
  @Resource
  private RemoteService ribbonService;
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
    ArrayList res = ribbonService.postCall(service, params, ArrayList.class);
    Object collect = res.stream().limit(10).collect(Collectors.toList());
    // TODO 查询
    log.info("end remote call {}", (System.currentTimeMillis() - start));
    return ResResult.succ(collect);
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
