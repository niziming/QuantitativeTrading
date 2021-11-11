package cn.zm.strategy.web.entity;

import cn.zm.plus.utils.ObjectConvert;
import cn.zm.strategy.web.entity.vo.FundBasicInfoVO;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("fund_basic_info")
@ApiModel(value = "基金基本信息", description = "基金基本信息")
public class FundBasicInfo extends ObjectConvert<FundBasicInfoVO> {
  @ApiModelProperty("基金代码")
  private String 基金代码;

  @ApiModelProperty("拼音缩写")
  private String 拼音缩写;

  @ApiModelProperty("基金简称")
  private String 基金简称;

  @ApiModelProperty("基金类型")
  private String 基金类型;

  @ApiModelProperty("拼音全称")
  private String 拼音全称;
}
