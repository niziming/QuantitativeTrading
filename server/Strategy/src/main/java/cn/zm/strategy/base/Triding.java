package cn.zm.strategy.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ApiModel("交易实体")
public class Triding {
  @ApiModelProperty("id")
  private String id;

  @ApiModelProperty("交易时间")
  private String time;

  @ApiModelProperty("交易类型")
  private String type;

  @ApiModelProperty("交易金额")
  private Double cash;
}
