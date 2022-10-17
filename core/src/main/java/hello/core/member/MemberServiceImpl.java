package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 더 이상 MemoryMemberRepository를 의존하지 않는다. MemberRepository만 의존한다.
    private final MemberRepository memberRepository;

    /**
     * MemberServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입 될지)는 알 수 없다.
     * 생성자를 통해서 어떤 구현 객체를 주입할지는 AppConfig가 결정한다.
     * 그래서 이젠 의존관계의 책임은 AppConfig에게 맡기고 실행만 하면 된다.
     * @param memberRepository
     */
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(int memberId) {
        return memberRepository.findById(memberId);
    }
}
