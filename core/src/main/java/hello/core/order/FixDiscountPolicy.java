package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {
    private int discountFixAmount = 1000;

    // 이 클래스는 SRP를 잘 지키고 있는 클래스임. 왜냐? 주문까지 갈 필요없이 여기서 할인을 바꿀 수 있기 때문.
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
