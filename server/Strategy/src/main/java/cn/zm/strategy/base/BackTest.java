package cn.zm.strategy.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel("回测参数")
public class BackTest {

  @ApiModelProperty("加仓阈值")
  private Float add = 0.15f;

  @ApiModelProperty("减仓阈值")
  private Float sub = 0.2f;

  @ApiModelProperty("模式")
  private String mode;

  @ApiModelProperty("开始时间")
  private String startTime;

  @ApiModelProperty("结束时间")
  private String endTime;

  @ApiModelProperty("初始资金")
  private Double initCash;

  @ApiModelProperty("运作资金")
  private Double usageCash;

  @ApiModelProperty("加仓率")
  private Float addRate = 0.2f;

  @ApiModelProperty("减仓率")
  private Float subRate = 0.15f;

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

}
