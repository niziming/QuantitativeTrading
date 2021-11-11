package cn.zm.strategy.web.entity;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "开放式基金-历史数据-入参", description = "开放式基金-历史数据-入参")
public class OpenFundHisParam {
  @ApiModelProperty("基金编码")
  private String fund;
  @ApiModelProperty("指标")
  private String indicator;
}
