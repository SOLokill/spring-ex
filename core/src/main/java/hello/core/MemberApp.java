package hello.core;

import hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        기존의 MemberService 객체에게 MemberServiceImpl을 주입해 만들던 방식에서,
//        AppConfig 객체를 만드는 방식으로 변경. 이제 appConfig가 결정.
//        MemberService memberService = new MemberServiceImpl();
//        AppConfig appConfig = new AppConfig();
//        스프링 컨테이너를 만든다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // memberService 객체에 AppConfig에서 만든 객체 대입
//        MemberService memberService = appConfig.memberService();
//        어떤 걸 찾아올지, 이름을 주어서 뭘 꺼낼지 정해줘야 한다. 기본적인 이름은 메서드 이름으로 된다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // 기존의 member 추가와 member 조인은 변경 없음.
        Member member = new Member(1, "soul kim", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1);
        System.out.println("new member = " + member.getMemberName());
        System.out.println("find member = " + findMember.getMemberName());
    }
}
