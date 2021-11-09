package cn.zm.gateway.config;

import cn.zm.gateway.filter.RequestTimeFilter;
import cn.zm.gateway.filter.TokenFilter;
import cn.zm.gateway.filter.factory.RequestTimeGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: <br>
 * <路由配置>
 *
 * @author 十渊
 * @date 2021/11/2 16:18
 * @return
 */
@Configuration
public class RouteConfig {
  private final String URL = "http://httpbin.org";
  private final String URL1 = "http://www.baidu.com";
  private final String URL2 = "/error";
  private final String FORWARD = "/gateway/fallback";

  @Bean
  public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
    return new RequestTimeGatewayFilterFactory();
  }

  @Bean
  public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
    // @formatter:off
    return builder.routes()
      .route(r -> r.path("/customer/**")
        .filters(f -> f.filter(new RequestTimeFilter())
          .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
        .uri("http://httpbin.org:80/get")
        .order(0)
        .id("customer_filter_router")
      )
      .build();
    // @formatter:on
  }


  @Bean
  public TokenFilter tokenFilter(){
    return new TokenFilter();
  }


  // @Bean
  // public RouteLocator gatewayRoute(RouteLocatorBuilder builder) {
  //   return builder.routes()
  //     .route(p -> p
  //       .path("/get")
  //       .filters(f -> f.addRequestHeader("Hello", "World"))
  //       .uri(URL))
  //     .route(p -> p
  //       // .host("*.hystrix.com")
  //       .path("/err")
  //       .filters(f -> f
  //         .hystrix(config -> config
  //           .setName("mycmd")
  //           .setFallbackUri("forward:/fallback")))
  //       .uri(URL2))
  //     .build();
  // }
}
