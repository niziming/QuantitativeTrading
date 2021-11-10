package cn.zm.strategy.web.rest;

import cn.zm.common.base.ResResult;
import cn.zm.common.config.GlobalConfig;
import cn.zm.plus.base.BaseController;
import cn.zm.strategy.web.service.RibbonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * 
 * @author 十渊
 * @since 2021-10-12
 */
@RequestMapping("test")
@RestController
@Api(tags = "plus测试接口")
public class TestController extends BaseController {

    // @Resource
    private GlobalConfig globalConfig;

    @Resource
    private RibbonService ribbonService;

    private static String services = "";

    @Autowired
    public TestController (GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
        services = globalConfig.getConfig()
          .getJSONObject("services").values()
          .stream()
          .map(a -> a.toString())
          .collect(Collectors.joining(","));
    }
    @GetMapping
    @ApiOperation("获取全局配置")
    public ResResult getConfig() {
        // TODO 查询
        return ResResult.succ(globalConfig);
    }

    @GetMapping("stockZhANew")
    @ApiOperation("stockZhANew")
    public ResResult stockZhANew() {
        // TODO 查询
        return ResResult.succ(ribbonService.stockZhANew());
    }

    @GetMapping("/services")
    @ApiOperation("获取所有服务")
    @ApiImplicitParams(
      @ApiImplicitParam(
        name = "serviceName",
        value = "服务名称",
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
        return ResResult.succ(ribbonService.stockZhANew());
    }


}
