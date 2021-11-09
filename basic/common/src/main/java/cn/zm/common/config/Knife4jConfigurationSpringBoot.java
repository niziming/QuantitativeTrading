// package cn.zm.common.config;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.bind.annotation.RestController;
// import springfox.documentation.builders.ApiInfoBuilder;
// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
//
// /** 功能描述: <br>
//  * <Knife4j 配置>
//  *
//  * @author 十渊
//  * @date 2021/10/12 11:23
//  * @return
//  */
// @Configuration
// @EnableSwagger2WebMvc
// public class Knife4jConfigurationbck {
//     @Bean(value = "defaultApi2")
//     public Docket defaultApi2() {
//         return new Docket(DocumentationType.SWAGGER_2)
//                 .apiInfo(new ApiInfoBuilder()
//                         .title("SpringCloud种子")
//                         .description("seed")
//                         .version("1.0")
//                         .build())
//                 //分组名称
//                 .groupName("1.0.0版本")
//                 .select()
//                 //这里指定Controller扫描包路径
//                 // .apis(RequestHandlerSelectors.any())
//                 // .apis(RequestHandlerSelectors.basePackage("cn.zm.web.rest"))
//                 .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                 .paths(PathSelectors.any())
//                 .build();
//     }
// }
