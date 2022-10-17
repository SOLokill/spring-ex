package hello.core.member;

public class Member {
    private int memberId;
    private String memberName;
    private Grade grade;

    public Member(int memberId, String memberName, Grade grade) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.grade = grade;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
