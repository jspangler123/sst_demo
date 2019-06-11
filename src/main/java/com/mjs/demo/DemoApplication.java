package com.mjs.demo;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages={"com.mjs.demo"})
@EnableJpaRepositories(basePackages={"com.mjs.demo.contacts.repositories"})
@EnableWebMvc
@Configuration
@EnableSwagger2
public class DemoApplication implements WebMvcConfigurer{

  @Bean
  public Docket api() { 
    return new Docket(DocumentationType.SWAGGER_2)  
      .select()                                  
      .apis(RequestHandlerSelectors.any())              
      .paths(PathSelectors.any())                          
      .build();                                           
  }
  
  @Bean
  public DataSource embeddedDataSource() {
      return new EmbeddedDatabaseBuilder()
          .setType(EmbeddedDatabaseType.H2).build();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
   
      registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }  
  
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
