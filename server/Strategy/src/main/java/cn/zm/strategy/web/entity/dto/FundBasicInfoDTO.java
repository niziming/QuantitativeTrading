package cn.zm.strategy.web.entity.dto;

import cn.zm.strategy.web.entity.FundBasicInfo;
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
    @ApiModelProperty(value = "基金基本信息")
    private String 基金代码;
    private String 拼音缩写;
    private String 基金简称;
    private String 基金类型;
    private String 拼音全称;
}

