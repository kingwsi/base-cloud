package com.example.common.configuration;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * description: 对swagger base url处理，网关转发后保证其url正确 <br>
 * date: 2021/07/05 14:01 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
@Profile({"dev", "test"})
public class SwaggerServerUrlFilter implements WebMvcOpenApiTransformationFilter {

    public static final String X_FORWARDED_PREFIX_HEADER = "X-Forwarded-Prefix";

    @Value("${spring.profiles.active}")
    private String active;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("BASE-SERVICE")
                .termsOfServiceUrl("https://github.com/kingwsi")
                .version("0.1")
                .build();
    }

    /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", In.HEADER.toValue());
        return Collections.singletonList(apiKey);
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(new SecurityReference("Authorization", new AuthorizationScope[]{new AuthorizationScope("global", "")})))
                        .build()
        );
    }

    @Override
    public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
        OpenAPI openApi = context.getSpecification();
        HttpServletRequest request = context.request().orElse(null);
        if (request != null && CollectionUtils.isNotEmpty(openApi.getServers())) {
            String prefix = request.getHeader(X_FORWARDED_PREFIX_HEADER);
            if (!StringUtils.isEmpty(prefix)) {
                for (Server server : openApi.getServers()) {
                    if ("test".equalsIgnoreCase(active)) {
                        server.setUrl("https://homec.club:8880/base-api" + prefix);
                    } else {
                        server.setUrl(server.getUrl() + prefix);
                    }

                }
            }
        }
        return openApi;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return delimiter == DocumentationType.OAS_30;
    }
}
