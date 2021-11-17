package cn.zm.strategy.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel("回测参数")
public class BackTest {

  @ApiModelProperty("买计数器")
  private Integer addCount = 0;

  @ApiModelProperty("卖计数器")
  private Integer subCount = 0;

  @ApiModelProperty("加仓阈值")
  private Float add = -0.025f;

  @ApiModelProperty("最后一次净值")
  private Double lastValue = 1.0d;

  @ApiModelProperty("减仓阈值")
  private Float sub = 0.03f;

  @ApiModelProperty("模式")
  private String mode;

  @ApiModelProperty("持仓")
  private Double depot = 0.0d;

  @ApiModelProperty("开始时间")
  private String startTime;

  @ApiModelProperty("结束时间")
  private String endTime;

  @ApiModelProperty("初始资金")
  private Double initCash = 5000d;

  @ApiModelProperty("运作资金")
  private Double usageCash = 0d;

  @ApiModelProperty("加仓率")
  private Float addRate = 0.15f;

  @ApiModelProperty("减仓率")
  private Float subRate = 0.2f;

  @ApiModelProperty(value = "死线", notes = "防止突然暴跌")
  private Double deadLine;

  @ApiModelProperty("加仓深度")
  private Integer addDepth = 4;

  @ApiModelProperty("减仓深度")
  private Integer subDepth = 4;

  @ApiModelProperty("卖出次数")
  private Integer sellTime;

  @ApiModelProperty("买入次数")
  private Integer bueTime;

  @ApiModelProperty("交易组")
  private List<Triding> tridings = new ArrayList<>();

}
