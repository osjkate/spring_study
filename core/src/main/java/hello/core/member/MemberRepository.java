package hello.core.member;

// 저장소와 연결되는 기능
public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
