package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 구현체가 하나면 인터페이스명 + Impl 이라고 씀
@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // 어떤 memberRepository가 들어올지 아무도 모름 -> config에서 정해줌
    @Autowired // ac.getBean(MemberRepository.class) 처럼 동작함
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
