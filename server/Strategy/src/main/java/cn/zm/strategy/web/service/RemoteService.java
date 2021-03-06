package cn.zm.strategy.web.service;

import java.util.List;

public interface RemoteService {
    /** 功能描述: <br>
     * <HTTP post 方法 远程调用>
     *
     * @author 十渊
     * @date 2021/11/10 14:09
     * @return java.util.List
     */
    <T> T postCall(String service, Object params, Class<T> type);
}
