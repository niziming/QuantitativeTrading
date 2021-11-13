package cn.zm.strategy.web.entity;

import cn.zm.plus.utils.ObjectConvert;
import cn.zm.strategy.web.entity.vo.FundBasicInfoVO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("fund_basic_info")
@ApiModel(value = "基金基本信息", description = "基金基本信息")
public class FundBasicInfo extends ObjectConvert<FundBasicInfoVO> {
  @TableId(type = IdType.ID_WORKER_STR)
  @ApiModelProperty("基金代码")
  private String fundCode;

  @ApiModelProperty("拼音缩写")
  private String fundAbbr;

  @ApiModelProperty("基金简称")
  private String fundIntro;

  @ApiModelProperty("基金类型")
  private String fundType;

  @ApiModelProperty("拼音全称")
  private String fundPinyin;
}
