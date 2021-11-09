package cn.zm.netty.service.impl;

import cn.zm.netty.handler.NettyHandler;
import cn.zm.netty.service.PushService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class PushServiceImpl implements PushService {
    @Override
    public void pushMsgToOne(String userId, String msg) {
        ConcurrentHashMap<String, Channel> userChannelMap = NettyHandler.getUserChannelMap();
        Channel channel = userChannelMap.get(userId);
        Assert.notNull(channel, userId+"-用户不存在");
        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

    @Override
    public void pushMsgToAll(String msg) {
        NettyHandler.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }

    public static void main(String[] args) {
        // ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        //
        // long initialDelay = 1;
        // long period = 2;
        // // 从现在开始1秒钟之后，每隔1秒钟执行一次job1
        // service.scheduleAtFixedRate(() -> {
        //     System.out.println("period");
        // }, initialDelay, period, TimeUnit.SECONDS);
        System.out.println(4/1000);
        // 从现在开始2秒钟之后，每隔2秒钟执行一次job2
        // service.scheduleWithFixedDelay(new MyScheduledExecutor("job2"), initialDelay, period, TimeUnit.SECONDS);
    }
}
