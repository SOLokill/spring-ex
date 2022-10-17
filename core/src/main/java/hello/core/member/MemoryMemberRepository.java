package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    // HashMap은 동시성 이슈 발생 가능, ConcurrenHashMap을 사용하는 것이 좋음
    private static Map<Integer, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getMemberId(), member);
    }

    @Override
    public Member findById(int memberId) {
        return store.get(memberId);
    }
}
