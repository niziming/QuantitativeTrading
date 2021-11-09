package cn.zm.netflix.ribbon.web.rest;

import cn.zm.common.base.ResResult;
import cn.zm.netflix.ribbon.web.service.RibbonService;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 
 * @author 十渊
 * @since 2021-10-12
 */
@RequestMapping("ribbon")
@RestController
@Api(tags = "ribbon负载均衡测试接口")
public class RibbonController {
    @Resource
    RibbonService ribbonService;

    @GetMapping
    @ApiOperation("查询测试")
    public ResResult get() {
        return ResResult.succ(JSON.parse(ribbonService.consume()));
    }

}
