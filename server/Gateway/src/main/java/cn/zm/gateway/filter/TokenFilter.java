package cn.zm.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 功能描述: <br>
 * <校验请求头token过滤器>
 *
 * @author 十渊
 * @date 2021/11/8 11:20
 * @return
 */
public class TokenFilter implements GlobalFilter, Ordered {
  Logger log = LoggerFactory.getLogger(TokenFilter.class);

  /** 功能描述: <br>
   * <请求头中自动获取token后测试>
   *
   * @param exchange
   * @param chain
   *
   * @author 十渊
   * @date 2021/11/8 11:27
   * @return reactor.core.publisher.Mono<java.lang.Void>
   */
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    String token = exchange.getRequest().getQueryParams().getFirst("token");
    if (token == null || token.isEmpty()) {
      log.info( "token is empty..." );
      exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
      return exchange.getResponse().setComplete();
    }
    return chain.filter(exchange);
  }

  /** 功能描述: <br>
   * <根据源码可知,越低的值具有越高的优先级>
   *
   * @author 十渊
   * @date 2021/11/8 11:23
   * @return int
   */
  @Override
  public int getOrder() {
    return -100;
  }

}
