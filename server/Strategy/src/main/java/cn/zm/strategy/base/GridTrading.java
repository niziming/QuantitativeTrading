package cn.zm.strategy.base;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel("网格交易")
public class GridTrading {
  private BackTest backTest;

  /** 功能描述: <br>
   * <交易>
   *
   * @param list
   * @param backTest
   *
   * @author 十渊
   * @date 2021/11/16 16:44
   * @return java.util.List<cn.zm.strategy.base.Triding>
   */
  public static List<Triding> trading(List<FundHisData> list, BackTest backTest) {
    // 初始化
    Integer currentIndex = 0;
    FundHisData currentDate;
    Triding trading = Triding.builder().build();
    List<Triding> tridings = new ArrayList<>();

    // 交易参数
    Double cash;
    // 交易类型
    String type;

    // 运作资金赋值
    backTest.setUsageCash(backTest.getInitCash());

    for (int i = list.size() - 1; i >= 0; i--) {
      // 赋值索引
      currentDate = list.get(++currentIndex);

      // 如果下跌超过加仓阈值 买入
      if (currentDate.getRate() < backTest.getAdd()) {
        // 交易金额
        cash = backTest.getUsageCash() * backTest.getAddRate();
        // 交易
        trading.setCash(cash);
        backTest.setUsageCash(backTest.getUsageCash() - cash);
      // 如果上涨超过减仓阈值 卖出
      } else if (currentDate.getRate() > backTest.getSub()) {
        // 交易金额
        cash = backTest.getUsageCash() * backTest.getSubRate();
      }

      // 交易实体
      // trading.setCash();
      // backTest.setUsageCash(backTest.getUsageCash() - cash);

      tridings.add(trading);
    }

    return null;
  }

  public Triding sell() {
  //
 return null;
  }



}
