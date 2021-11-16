package cn.zm.strategy.web.rest;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.zm.common.base.ResResult;
import cn.zm.common.config.GlobalConfig;
import cn.zm.plus.base.BaseController;
import cn.zm.strategy.base.*;
import cn.zm.strategy.web.entity.FundBasicInfo;
import cn.zm.strategy.web.entity.OpenFundHisParam;
import cn.zm.strategy.web.entity.TradingParam;
import cn.zm.strategy.web.entity.dto.FundBasicInfoDTO;
import cn.zm.strategy.web.entity.vo.FundBasicInfoVO;
import cn.zm.strategy.web.service.IFundBasicInfoService;
import cn.zm.strategy.web.service.RemoteService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 基金基本信息
 * @author 十渊
 * @since 2021-11-11
 */
@RequestMapping("fundBasicInfo")
@RestController
@Slf4j
@Api(tags = "基金基本信息")
@Transactional(rollbackFor = Exception.class)
public class FundBasicInfoController extends BaseController {
    @Resource
    private RemoteService remoteService;
    @Resource
    private IFundBasicInfoService fundBasicInfoService;

    private static JSONObject services;

    public FundBasicInfoController(GlobalConfig globalConfig){
        services = globalConfig.getConfig().getJSONObject("services");
    }

    @GetMapping
    @ApiOperation("基金基本信息page查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1"),
        @ApiImplicitParam(name = "size", value = "每页个数", defaultValue = "10"),
        @ApiImplicitParam(name = "orderByColumn", value = "排序字段"),
        @ApiImplicitParam(name = "isDesc", value = "是否降序")
    })
    public ResResult<IPage<FundBasicInfoVO>> getByPage(@Validated FundBasicInfoDTO fundBasicInfo) {
        // TODO 分页查询
        IPage<FundBasicInfoVO> page = fundBasicInfoService.selectByPage(getPage(), fundBasicInfo);
        return ResResult.succ(page);
    }

    @PostMapping("/history")
    @ApiOperation("基金基本信息-历史数据查询")
    public ResResult<List<FundHisData>> getHisByFund(@RequestBody OpenFundHisParam fundParam) {
        log.info(fundParam.toString());
        ArrayList<Map> result = remoteService.postCall("fund_em_open_fund_info", fundParam, ArrayList.class);
        List<FundHisData> collect = result
                .stream()
                .map(r ->
                  FundHisData.builder()
                    .rate((Double) r.get("单位净值"))
                    .value((Double) r.get("单位净值"))
                    .time(DateUtil.format(new Date((Long) r.get("净值日期")), "yyyy-MM-dd"))
                    .build())
                .collect(Collectors.toList());
        return ResResult.succ(collect);
    }

    @PostMapping("/trading/grid")
    @ApiOperation("网格交易")
    public ResResult<BackTest> tradingGrid(@RequestBody TradingParam tradingParam) {

        ArrayList<Map> result = remoteService.postCall("fund_em_open_fund_info", tradingParam.getFundParam(), ArrayList.class);
        List<FundHisData> collect = result
                .stream()
                .map(r ->
                        FundHisData.builder()
                                .rate((Double) r.get("单位净值"))
                                .value((Double) r.get("单位净值"))
                                .time(DateUtil.format(new Date((Long) r.get("净值日期")), "yyyy-MM-dd"))
                                .build())
                .collect(Collectors.toList());

        BackTest backTestParam = tradingParam.getBackTest();
        backTestParam.setTridings(new ArrayList<Triding>());
        BackTest backTest = GridTrading.trading(collect, backTestParam);

        backTest.getTridings().forEach(
            m -> {
                m.setColor(m.getType().equals("买") ? "green" : "red");
                m.setText(m.getType()+Math.floor(m.getCash()));
                m.setShape(m.getType().equals("买") ? "arrowDown" : "arrowUp");
                m.setPosition(m.getType().equals("买") ? "belowBar" : "aboveBar");
            }
        );
        List<Triding> buyTime = backTest.getTridings().stream().filter(b -> b.getType().equals("买")).collect(Collectors.toList());
        backTest.setBueTime(buyTime.size());
        backTest.setSellTime(backTest.getTridings().size() - buyTime.size());
        return ResResult.succ(backTest);
    }

    // @ApiOperation("基金基本信息同步接口")
    // @GetMapping("/getBasicInfo")
    // public ResResult getBasicInfoSync() {
    //     // 获取配置 查询参数
    //     JSONObject req = services.getJSONObject("0");
    //     String url = req.getString("url");
    //     String param = req.getString("param");
    //
    //     // 调用远程接口查询所有基金数据
    //     ArrayList<FundBasicInfo> res = remoteService.postCall(url, param, ArrayList.class);
    //     List<String> ids = res.stream().map(r -> r.getFundCode()).collect(Collectors.toList());
    //     // 查询数据库存在的数据
    //     List<String> collect = fundBasicInfoService
    //       .listByIds(ids)
    //       .stream()
    //       .map(FundBasicInfo::getFundCode)
    //       .collect(Collectors.toList());
    //
    //     res.removeAll(collect);
    //
    //     // // 批量存储
    //     // int size = 0;
    //     // while (size < res.size()) {
    //     //     List<FundBasicInfo> batch = res.stream()
    //     //       .skip(size)
    //     //       .limit(500)
    //     //       .collect(Collectors.toList());
    //     //     fundBasicInfoService.saveBatch(batch);
    //     //     log.info("批量存储{}", 500);
    //     //     size += 500;
    //     // }
    //     return ResResult.succ("同步成功");
    // }
    //
    // @GetMapping("{fundCode}")
    // @ApiOperation("基金基本信息查询(fundCode)")
    // public ResResult<FundBasicInfoVO> get(@PathVariable String fundCode) {
    //     FundBasicInfo res = fundBasicInfoService.getById(fundCode);
    //     return ResResult.succ(res == null ? null : res.convert());
    // }
    //
    // @PostMapping
    // @ApiOperation("基金基本信息新增")
    // public ResResult add(@RequestBody @Validated FundBasicInfoDTO fundBasicInfo) {
    //     // TODO 新增
    //     fundBasicInfoService.save(fundBasicInfo.convert());
    //     return ResResult.succ("新增成功");
    // }
    //
    // @DeleteMapping("{id}")
    // @ApiOperation("基金基本信息删除")
    // public ResResult del(@PathVariable String id) {
    //     // TODO 删除
    //     fundBasicInfoService.removeById(id);
    //     return ResResult.succ("删除成功");
    // }
    //
    // @PutMapping
    // @ApiOperation("基金基本信息修改")
    // public ResResult update(@RequestBody @Validated FundBasicInfoDTO fundBasicInfo) {
    //     // TODO 修改
    //     fundBasicInfoService.updateById(fundBasicInfo.convert());
    //     return ResResult.succ("修改成功");
    // }
}
