package cn.zm.strategy.web.rest;

import cn.zm.common.base.ResResult;
import cn.zm.common.config.GlobalConfig;
import cn.zm.plus.base.BaseController;
import cn.zm.strategy.web.service.RibbonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 * 
 * @author 十渊
 * @since 2021-10-12
 */
@RequestMapping("test")
@RestController
@Api(tags = "plus测试接口")
public class TestController extends BaseController {

    @Resource
    private GlobalConfig globalConfig;

    @Resource
    private RibbonService ribbonService;

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


}
