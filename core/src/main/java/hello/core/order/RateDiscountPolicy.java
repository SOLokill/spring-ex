package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    /**
     * 실제 실무에서는 이런 현금과 관련된 로직은 테스트도 엄청 많고, 경계값 테스트도 엄청 한다.
     * @param member
     * @param price
     * @return
     */
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
