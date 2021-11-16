package cn.zm.strategy.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("基金历史实体")
public class FundHisData {

  @ApiModelProperty("净值日期")
  private String time;

  @ApiModelProperty("日增长率")
  private Double rate;

  @ApiModelProperty("单位净值")
  private Double value;
}
