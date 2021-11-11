package cn.zm.strategy.web.service.impl;

import cn.zm.strategy.web.entity.FundBasicInfo;
import cn.zm.strategy.web.entity.dto.FundBasicInfoDTO;
import cn.zm.strategy.web.entity.vo.FundBasicInfoVO;
import cn.zm.strategy.web.mapper.FundBasicInfoMapper;
import cn.zm.strategy.web.service.IFundBasicInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.zm.plus.utils.ConvertUtil;


@Service
@Transactional(rollbackFor = Exception.class)
public class FundBasicInfoServiceImpl extends ServiceImpl<FundBasicInfoMapper, FundBasicInfo> implements IFundBasicInfoService {
    @Override
    public IPage<FundBasicInfoVO> selectByPage(IPage<FundBasicInfo> page, FundBasicInfoDTO fund_basic_info) {
        IPage<FundBasicInfo> pageBean = baseMapper.selectPage(page, new QueryWrapper<>(fund_basic_info.convert()));
        return ConvertUtil.buildPage(pageBean);
    }
}
