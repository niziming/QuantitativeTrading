package cn.zm.strategy.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 功能描述: <br>
 * <指标枚举>
 *
 * @author 十渊
 * @date 2021/11/11 10:26
 * @return
 */
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Indicator", description = "指标")
public enum Indicator {
  UNIT_EQUITY_TREND (0, "单位净值走势"),
  CUMULATIVE_NET_WORTH_TREND(1, "累计净值走势"),
  CUMULATIVE_RATE_OF_RETURN_TREND (2, "累计收益率走势"),
  SIMILAR_RANKING_TRENDS (2, "同类排名走势"),
  PERCENTAGE_OF_SIMILAR_RANKING (2, "同类排名百分比"),
  DIVIDEND_DISTRIBUTION_DETAILS (2, "分红送配详情"),
  SPLIT_DETAILS (2, "拆分详情")
  ;
  @ApiModelProperty(value = "指标编码", example = "1")
  private Integer code;
  @ApiModelProperty(value = "指标名称", example = "单位净值走势")
  private String label;

  public Integer getCode() {
    return code;
  }

  public String getLabel() {
    return label;
  }
}
