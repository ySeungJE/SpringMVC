package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


// 내가 이해하기에는 이 config 클래스가 의존주입을 해주는? 그런 느낌인거 같애
// @Configuration 을 붙여야만 완전한 싱글톤 컨테이너를 지원해준다.
// @Configuration 을 붙임으로써 스프링은 CGLIB를 이용하여 임의로 AppConfig의 자식 클래스를 만들고 그것을 스프링 빈으로 등록한다
// 그 자식 클래스 내에선 이미 컨테이너에 등록된 Bean 객체가 다시 생성되려 할 때, 컨테이너 안의 객체를 반환한다
// 즉 모든 스프링 설정 정보에는 @Configuration을 등록해주면 된다
@Configuration
@ComponentScan (
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class, // 이 클래스가 있는 패키지, 즉 hello.core 패키지를 찾는다
        // 지정을 안하면? @ComponentScan이 붙어있는 클래스가 속한 패키지를 찾는다
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}

