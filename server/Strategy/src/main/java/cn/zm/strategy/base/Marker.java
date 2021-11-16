package cn.zm.strategy.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@ApiModel("标记实体")
public class Marker {
  @ApiModelProperty("id")
  private String id;

  @ApiModelProperty("时间")
  private String time;

  @ApiModelProperty("位置")
  private String position;

  @ApiModelProperty("颜色")
  private String color;

  @ApiModelProperty(value = "形状", notes = "1:arrowUp 2:arrowDown")
  private String shape;

  @ApiModelProperty("文本")
  private String text;

}
