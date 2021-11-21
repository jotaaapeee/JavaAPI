package org.serratec.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class swaggerConfig {
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.serratec.ecommerce"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo());
    }

    public ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("API Projeto Final")
                .description("Ecommerce :D")
                .license("Apache 2.0")
                .version("1.0.0")
                .contact(new Contact("Serratec", "hhtp://www.serratec.org", "serratec@serratec.org"))
                .build();

        return apiInfo;
    }

    
}
