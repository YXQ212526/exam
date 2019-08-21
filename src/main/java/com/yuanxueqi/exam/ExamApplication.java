package com.yuanxueqi.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ExamApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExamApplication.class, args);
  }

  @Bean
  public Docket createExample()
  {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(myInfo());
  }

  private ApiInfo myInfo(){
    Contact contact=new Contact("雪琪","无","18804621950@163.com");
    return new ApiInfoBuilder()
        .contact(contact)
        .description("测试")
        .title("充值")
        .build();
  }
}
