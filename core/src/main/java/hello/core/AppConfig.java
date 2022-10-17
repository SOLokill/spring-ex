package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스를 만들자.
 * AppConfig를 통해 관심사(역할)을 확실하게 분리했다.
 * 이 클래스는 구체 클래스를 선택한다. 애플리케이션이 어떻게 동작해야 할지 전체 구성 
 */
@Configuration
public class AppConfig {
    /**
     * 애플리케이션 실제 동작에 필요한 구현 객체를 생성한다. 생성자 주입.
     * MemberService가 interface MemberRepository를 save()하고,
     * interface에 엮여있는 MemoryMemberRepository와 연결해준다.
     */
//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    } -> 리팩터링 진행
//  new MemoryMemberRepository 이 부분이 중복 제거 되었다.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    /**
     * 애플리케이션 실제 동작에 필요한 구현 객체를 생성한다.
     */
//    public OrderService orderService() {
//        return new OrderServiceImpl(
//                new MemoryMemberRepository(),
//                new FixDiscountPolicy());
//    } -> refactoring processing
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    //  refactoring 진행하며, 기존의 AppConfig를 더 쪼개어
    //  MemoryMemberRepository를 다른 구현체로 변경할 때 한 부분만 변경하면 된다.
//  리팩터링을 진행하며 역할(Service)와 구현(Repo, Policy)로 한눈에 들어온다.
//  애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있다.
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
//        그래서 FixDiscountPolicy에서 RateDiscountPolicy로 변경할 때 구성 영역(Config)만 영향을 받고, 사용 영역(Impl)은 전혀 영향받지 않는다.
        return new RateDiscountPolicy();
//        이제 할인 정책을 변경해도, 애플리케이션의 구성 역할을 담당하는 AppConfig만 변경하면 된다.
//        클라이언트 코드린 OrderServiceImpl을 포함해서 사용 영역의 어떤 코드도 변경할 필요가 없다.
//        구성 영역(Config)는 당연히 변경된다. 기획자로 생각하면 더 쉽게 이해할 수 있다.
    }
    /**
     * 생성한 객체 인스턴스 참조(레퍼런스)를 생성자를 통해서 주입(연결)한다.
     * 각 클래스에 있는 생성자를 확인해보자.
     * @Configuration은 설정을 구성한다는 뜻이다.
     * @Bean은 스프링 컨테이너에 스프링 빈으로 등록한다는 뜻이다.
     */
}
