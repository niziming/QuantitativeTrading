package cn.zm.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * TextWebSocketFrame类型， 表示一个文本帧
 *
 * @author sixiaojie
 * @date 2020-03-28-13:47
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 一旦连接，第一个被执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("[{}]连接服务-当前连接数[{}]", ctx.channel().id().asLongText().substring(52), NettyHandler.getUserChannelMap().entrySet().size() + 1);

        // 获取用户ID,关联channel
        NettyHandler.getUserChannelMap().put(ctx.channel().id().asLongText(), ctx.channel());

        // 添加到channelGroup 通道组
        NettyHandler.getChannelGroup().add(ctx.channel());
        ctx.channel().writeAndFlush(new TextWebSocketFrame("客户端已注册,服务器连接成功！"));
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        log.info("channelRegistered");
        ctx.channel().writeAndFlush(new TextWebSocketFrame("客户端已注册,服务器连接成功！"));
    }

    /**
     * 读取数据
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String uid = ctx.channel().id().asLongText();
        // 获取用户ID,关联channel
        // JSONObject jsonObject = JSONUtil.parseObj(msg.text());
        // String uid = jsonObject.getStr("uid");
        // NettyHandler.getUserChannelMap().put(uid, ctx.channel());

        log.info("服务器收到[{}]的消息{}", uid.substring(52), msg.text());

        // 将用户ID作为自定义属性加入到channel中，方便随时channel中获取用户ID
        // AttributeKey<String> key = AttributeKey.valueOf("userId");
        // ctx.channel().attr(key).setIfAbsent(uid);

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("uid[{}]断开连接", ctx.channel().id().asLongText().substring(52));
        // 删除通道
        removeChannel(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("[{}]异常[{}]", ctx.channel().id().asLongText(), cause.getMessage());
        // 删除通道
        removeChannel(ctx);
    }

    /**
     * 删除用户与channel的对应关系
     *
     * @param ctx
     */
    private void removeChannel(ChannelHandlerContext ctx) {
        String uid = ctx.channel().id().asLongText();
        NettyHandler.getUserChannelMap().remove(uid);
        NettyHandler.getChannelGroup().remove(ctx.channel());
        ctx.close();
        log.info("当前连接数[{}]", NettyHandler.getUserChannelMap().entrySet().size());
    }
}