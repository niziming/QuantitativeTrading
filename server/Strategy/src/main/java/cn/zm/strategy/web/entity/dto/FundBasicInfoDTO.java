package cn.zm.strategy.web.entity.dto;

import cn.zm.strategy.web.entity.FundBasicInfo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.zm.plus.utils.ObjectConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("fund_basic_info")
@ApiModel(value="FundBasicInfoDTO对象", description="")
public class FundBasicInfoDTO extends ObjectConvert<FundBasicInfo>{
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

