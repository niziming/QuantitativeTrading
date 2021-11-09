package cn.zm.plus.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.stream.Collectors;

public class ConvertUtil {
    /**
     * 获取 vo 分页数据
     *
     * @param page do 分页数据
     * @return vo 分页数据
     */
    public static <T, E extends ObjectConvert> IPage<T> buildPage(IPage<E> page) {
        IPage<T> pageViews = new Page<>();
        BeanUtil.copyProperties(page, pageViews);
        pageViews.setRecords(page.getRecords()
               .stream()
               .map(e -> (T)e.convert())
               .collect(Collectors.toList()));
        return pageViews;
    }
}
