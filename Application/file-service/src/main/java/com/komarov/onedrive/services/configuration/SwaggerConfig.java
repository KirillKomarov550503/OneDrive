package com.komarov.onedrive.services.configuration;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket docket() {
    List<SecurityReference> references = Collections.singletonList(new SecurityReference("files",
        Stream.of(new AuthorizationScope("", ""))
            .toArray(AuthorizationScope[]::new)));
    List<SecurityContext> securityContexts = Collections
        .singletonList(SecurityContext.builder().securityReferences(references).build());
    return new Docket(DocumentationType.SWAGGER_2)
        .securitySchemes(Collections.singletonList(new BasicAuth("files")))
        .securityContexts(securityContexts)
        .select()
        .paths(PathSelectors.any())
        .apis(RequestHandlerSelectors.basePackage("com.komarov.onedrive"))
        .build();
  }
}
