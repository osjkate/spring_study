package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor        //final 이 붙은 필드값을 자동으로 넣어주는 생성자를 생성해줌, 생성자가 있으면 오류남 // 의존관계 추가할 때 개편함
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;        // final -> 생성자로 셋팅해야 함

    // DIP 위반 & OCP 위반하고 있음
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final DiscountPolicy discountPolicy;

    // 수정자 주입
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    // 생성자를 통해 어떤 인스턴스가 들어올 지 모르는 상황
                    // 생성자 위에다 설정하기 --> 생성자 주입 (생성자 호출하는 시점에 딱 한 번 호출됨 // 불편, 필수)
//    @Autowired      // @Autowired 생략해도 괜찮음
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;       // 기본 전략 : type 을 보고 스프링 빈에서 찾아서 의존관계 주입함
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member  = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
