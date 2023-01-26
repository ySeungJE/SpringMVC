package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // ApplicationContext 스프링을 쓸 때는 얘를 꼭 써야 함, 이 자체가 컨테이너나 다름없음.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 파라미터 안에는 보통 메서드 이름과, 반환 타입을 넣는다
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember.getName() = " + findMember.getName());

    }
}
