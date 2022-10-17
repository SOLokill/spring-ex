package hello.core.order;

import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

// impl이 클라이언트 역할.
public class OrderServiceImpl implements OrderService {
    /**
     * DIP를 지키려면 추상 의존에만 의존하도록 만들어야 한다. 즉, 구체 의존을 하면 안된다.
     * DIP 위반 중인 코드. OrderServiceImpl이 DiscountPolicy 와 FixDiscountPolicy(구체 클래스, 구현 클래스), DiscountPolicy(추상 의존, interface)를 의존하고 있다.
     * 기존의 이 코드는 OrderServic가 FixDiscountPolicy, RateDiscountPolicy 이 두 구체(구현) 클래스를 선택하는 역할(new FixDis..., new Memory...) 및 인터페이스 역할도 하고 있었다.
     * 고로 중간 다리가 필요하다. AppConfig를 만들면서 FixDiscountPolicy를 의존할 필요가 없어졌다.
     */
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    /**
     * 인터페이스에만 의존하도록 코드를 변경해도 구현체가 없어 문제다.
     * 왜냐하면 DiscountPolicy를 의존하게 만들면, createOrder()에서 discountPolicy 변수에 NULL값이 들어가는 경우가 있기 때문.
     * 그래서 누군가 OrderServiceImpl에 DiscountPolicy 구현 객체를 대신 생성 후에 주입해줄 수 있으면 DIP도 지키면서 NULLPointException도 해결 가능하다.
     * 하지만 이제 AppConfig에서 객체를 생성해주니 그저 선언해주면 된다.
     * 그래서 이제는 DiscountPolicy, MemberRepository 인터페이스만 의존한다.
     */
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    /**
     * 이런 방식으로 바꾸면 클라이언트 코드를 바꿔야 하니 OCP 위반이다.
     */
    // private final RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    /**
     * 생성자를 통해서 어떤 객체가 들어오는지는 AppConfig만이 알고 있다.
     * 또한 AppConfig만이 어떤 구현 객체를 주입할지 결정한다.
     * 이제 실행에만 집중할 수 있는 클라이언트가 만들어졌다.
     * @param memberRepository
     * @param discountPolicy
     */
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     * 이를 해결하기 위해서 클라이언트 OrderServiceImpl에 DiscountPolicy의 구현 객체를 생성하고 주입해주어야 한다.
     */

    @Override
    public Order createOrder(int memberId, String productName, int productPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, productPrice);
        return new Order(memberId, productName, productPrice, discountPrice);
    }
}
