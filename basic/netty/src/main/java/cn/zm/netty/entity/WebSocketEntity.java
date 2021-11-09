package cn.zm.netty.entity;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "WebSocketEntity消息交互实体")
public class WebSocketEntity {
    @ApiModelProperty("标识channel")
    private String uid;

    @ApiModelProperty("标识任务")
    private String taskid;

    @ApiModelProperty("标识本msg实体处理状态 [init, run, down, error]")
    private String status;

    @ApiModelProperty("标识功能名称")
    private String futures;

    @ApiModelProperty("标识交互的数据")
    private JSONObject data;

    @ApiModelProperty("保留字段")
    private String source;

    @ApiModelProperty("保留字段")
    private String target;

    @ApiModelProperty("保留字段")
    private String auth;
}
