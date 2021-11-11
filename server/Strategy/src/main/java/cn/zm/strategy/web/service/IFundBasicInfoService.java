package cn.zm.strategy.web.service;

import cn.zm.strategy.web.entity.FundBasicInfo;
import cn.zm.strategy.web.entity.dto.FundBasicInfoDTO;
import cn.zm.strategy.web.entity.vo.FundBasicInfoVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IFundBasicInfoService extends IService<FundBasicInfo> {
    /**
    * 分页查询
    *
    * @param page   分页信息
    * @param FundBasicInfo 基金基本信息入参
    * @return 分页结果
    */
    IPage<FundBasicInfoVO> selectByPage(IPage<FundBasicInfo> page, FundBasicInfoDTO FundBasicInfo);
}
