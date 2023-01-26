package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



// 내가 이해하기에는 이 config 클래스가 의존주입을 해주는? 그런 느낌인거 같애
// @Configuration 을 붙여야만 완전한 싱글톤 컨테이너를 지원해준다.
// @Configuration 을 붙임으로써 스프링은 CGLIB를 이용하여 임의로 AppConfig의 자식 클래스를 만들고 그것을 스프링 빈으로 등록한다
// 그 자식 클래스 내에선 이미 컨테이너에 등록된 Bean 객체가 다시 생성되려 할 때, 컨테이너 안의 객체를 반환한다
// 즉 모든 스프링 설정 정보에는 @Configuration을 등록해주면 된다
@Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("call AppConfig.discountPolicy");
        return new RateDiscountPolicy();
    }

}
