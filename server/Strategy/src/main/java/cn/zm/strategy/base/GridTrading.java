package cn.zm.strategy.base;


import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@ApiModel("网格交易")
public class GridTrading {
  private BackTest backTest;

  /**
   * 功能描述: <br>
   * <交易>
   *
   * @param list
   * @param backTest
   * @return java.util.List<cn.zm.strategy.base.Triding>
   * @author 十渊
   * @date 2021/11/16 16:44
   */
  public static BackTest trading(List<FundHisData> list, BackTest backTest) {
    if (list.size() < backTest.getSubDepth()) return backTest;

    // 运作资金赋值
    backTest.setUsageCash(backTest.getInitCash());
    backTest.setLastValue(list.get(list.size() - 1).getValue());

    for (int i = backTest.getSubDepth(); i < list.size(); i++) {
      //region init
      FundHisData prevDate;
      FundHisData currentDate = list.get(i);
      //endregion

      // 深度处理
      for (Integer deep = 1; deep < backTest.getSubDepth(); deep++) {
        prevDate = list.get(i - deep);

        Double decisionLast = null;

        if (backTest.getTridings().size() > 0){
          Triding triding = backTest.getTridings().get(backTest.getTridings().size() - 1);
          decisionLast = currentDate.getValue() - triding.getValue();
        }

        Double decisionValue = currentDate.getValue() - prevDate.getValue();

        if (Objects.nonNull(decisionLast) ? decisionLast < backTest.getAdd() && decisionValue < backTest.getAdd() : decisionValue < backTest.getAdd())
          // todo 买
          buy(backTest, currentDate);
        else if (Objects.nonNull(decisionLast) ? decisionLast > backTest.getSub() && decisionValue > backTest.getSub() : decisionValue > backTest.getSub())
          // todo 卖
          sell(backTest, currentDate);
      }
    }
    return backTest;
  }

  public static void sell(BackTest backTest, FundHisData fundHisData) {
    if (backTest.getDepot() <= 0) return;
    Integer weight = backTest.getSubCount() + 1;
    backTest.setSubCount(0);
    backTest.setAddCount(backTest.getAddCount() + 1);

    // todo 卖
    List<Triding> tridings = backTest.getTridings();
    // 运行资金
    Double usageCash = backTest.getUsageCash();
    // 减仓
    Double subDepot = backTest.getDepot() * backTest.getSubRate() * weight;
    Double sellCash = Math.floor(subDepot * fundHisData.getValue());

    backTest.setDepot(backTest.getDepot() - subDepot);

    backTest.setUsageCash(usageCash + sellCash);

    tridings.add(Triding.builder()
      .value(fundHisData.getValue())
      .time(fundHisData.getTime())
      .type("卖")
      .cash(sellCash)
      .build());
  }

  public static void buy(BackTest backTest, FundHisData fundHisData) {
    // todo 买
    if (backTest.getUsageCash() <= 0) return;

    Integer weight = backTest.getAddCount() + 1;
    backTest.setSubCount(0);
    backTest.setAddCount(backTest.getAddCount() + 1);



    List<Triding> tridings = backTest.getTridings();
    // 运行资金
    Double usageCash = backTest.getUsageCash();
    // 购买花费
    Double buyCash = Math.floor(usageCash * (backTest.getAddRate() * weight));
    // 加仓
    Double addDepot = buyCash / fundHisData.getValue();

    backTest.setDepot(backTest.getDepot() + addDepot);

    backTest.setUsageCash(usageCash - buyCash);

    tridings.add(Triding.builder()
      .value(fundHisData.getValue())
      .time(fundHisData.getTime())
      .type("买")
      .cash(buyCash)
      .build());
  }


}
