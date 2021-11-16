package cn.zm.strategy.web.entity;

import cn.zm.strategy.base.BackTest;
import lombok.Data;


@Data
public class TradingParam {
    OpenFundHisParam fundParam;
    BackTest backTest;
}
