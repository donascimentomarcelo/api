package br.com.api.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
	
	@Value("${swagger.basePackege}")
	private String basePackage;

	@Value("${swagger.pathSelectorsRegex}")
	private String pathSelectorsRegex;

	@Value("${swagger.title}")
	private String title;
	
	@Value("${swagger.description}")
	private String description;
	
	@Value("${swagger.version}")
	private String version;
    
	@Bean
    public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2).select()
	            .apis(RequestHandlerSelectors
	                .basePackage(basePackage))
	            .paths(PathSelectors.regex(pathSelectorsRegex))
	            .build().apiInfo(apiEndPointsInfo());                                          
    }
	

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
    		.title(title)
            .description(description)
            .version(version)
            .build();
    }
}
