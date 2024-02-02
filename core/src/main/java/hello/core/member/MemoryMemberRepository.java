package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// 자바 코드로만 저장함 (DB에 저장 아님)
@Component
public class MemoryMemberRepository implements MemberRepository{

    // 저장소
    // 참고 : 동시성 이슈가 발생할 수 있어서 원래는 concurrentHashMap을 써야 함.
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
